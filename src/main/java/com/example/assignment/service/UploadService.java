package com.example.assignment.service;

import com.example.assignment.model.Employee;
import com.example.assignment.repository.UploadRepo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UploadService {

    @Autowired
    private UploadRepo uploadRepo;
    private static final String COLLECTION_NAME = "RAW";


    //saving the csv file to the firestore
    public void saveCsv(MultipartFile file) throws ExecutionException, InterruptedException, IOException {
        System.out.println("step 2");
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        ArrayList<Employee> employees = new ArrayList<>();
        HashMap<String, Employee> hmap = new HashMap<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        ApiFuture<WriteResult> collectionapi = null;
        for (CSVRecord record : records) {
            Employee emp = new Employee();
            System.out.println("step 3");
            emp.setEmployeeNo(Long.parseLong(record.get(0)));
            System.out.println("step 4");
            emp.setEmployeeName(record.get(1));
            emp.setBasic(Integer.parseInt(record.get(2)));
            emp.setHRA(Integer.parseInt(record.get("HRA")));
            emp.setSupplementaryAllowance(Integer.parseInt(record.get(4)));
            emp.setCarMaintenance(Integer.parseInt(record.get(5)));
            emp.setBooksPeriodicals(Integer.parseInt(record.get(6)));
            emp.setPF(Integer.parseInt(record.get("PF")));
            emp.setIncomeTax(Integer.parseInt(record.get(8)));
            System.out.println(".........TEST      " + emp.getIncomeTax() + "........... " + emp.getBooksPeriodicals());
            employees.add(emp);
            String id = emp.getEmployeeNo() + "";
            collectionapi = dbFirestore.collection(COLLECTION_NAME).document(id).set(emp);
        }

        return;

    }

    @GetMapping("/csvstore")
    public List<Employee> getCsvFirestore() throws ExecutionException, InterruptedException {

        List<Employee> myarraylist = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentSnapshot> readChannelApiFuture;
        //getting the csv data,create the list of users, return that list and passs that to db
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFirestore.collection(COLLECTION_NAME).get();
        for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {

            Employee emp = doc.toObject(Employee.class);
            myarraylist.add(emp);
        }
        System.out.println("printing the list of emp obj");
        System.out.println(myarraylist);
//        readChannelApiFuture=dbFirestore.collection(COLLECTION_NAME).document().get();
        System.out.println("......................................................");
//        System.out.println(readChannelApiFuture.get().get("name"));

        return myarraylist;

    }

    public List<Employee> trigger_on_Csv(List<Employee> mylist) {
        //grosspay and netpay
        List<Employee> myemp = new ArrayList<>();
        int grosspay = 0;
        int netpay = 0;

        for (Employee emp : mylist) {

            grosspay = emp.getBasic()
                    + emp.getHRA()
                    + emp.getSupplementaryAllowance()
                    + emp.getCarMaintenance()
                    + emp.getBooksPeriodicals();
            emp.setGrossPay(grosspay);

            netpay = grosspay - (emp.getPF() + emp.getIncomeTax());
            emp.setNetpay(netpay);
            myemp.add(emp);
        }
        return myemp;
    }

    public void saveCsvtoDB(List<Employee> Modifiedcsv) {

        for (Employee emp : Modifiedcsv) {
            uploadRepo.save(emp);
        }
    }

    public List<Employee> getCsvfromDB() {

        List<Employee> myuser = new ArrayList<>();

        List<Employee> finalcsv = (List<Employee>) uploadRepo.findAll();

        System.out.println(finalcsv);

        return finalcsv;

    }
}
