package com.example.assignment.service;

import com.example.assignment.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.ReadChannel;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
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

    private static final String COLLECTION_NAME = "sampleData";


    //saving the csv file to the firestore
    public void saveCsv(MultipartFile file) throws ExecutionException, InterruptedException, IOException {
        System.out.println("step 3");
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        ArrayList<User> users = new ArrayList<>();
        HashMap<String, User> hmap = new HashMap<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        ApiFuture<WriteResult> collectionapi = null;
        for (CSVRecord record : records) {
            User user = new User();
            user.setId(Long.parseLong(record.get("id")));
            user.setEmail(record.get("email"));
            user.setName(record.get("name"));
            users.add(user);
            String id = user.getId() + "";
//            hmap.put(id, user);

            collectionapi = dbFirestore.collection(COLLECTION_NAME).document("testdoc").set(user);

        }

        return;

    }

    @GetMapping("/csvstore")
    public void getCsvFirestore() throws ExecutionException, InterruptedException {
        System.out.println("step 2");
        System.out.println(".............................");
        List<User> myarraylist=new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentSnapshot> readChannelApiFuture;
    //getting the csv data,create the list of users, return that list and passs that to db
      ApiFuture<QuerySnapshot> querySnapshotApiFuture=  dbFirestore.collection(COLLECTION_NAME).get();
      for(DocumentSnapshot doc:querySnapshotApiFuture.get().getDocuments()){

          User user=doc.toObject(User.class);
          myarraylist.add(user);
          System.out.println(user);
      }
        System.out.println(myarraylist);
        readChannelApiFuture=dbFirestore.collection(COLLECTION_NAME).document().get();
        System.out.println("......................................................");
//        System.out.println(readChannelApiFuture.get().get("name"));
    }

    public void saveCsvtoDB() {



    }

    public List<User> getCsvfromDB() {

        List<User> myuser=new ArrayList<>();

        return myuser;

    }
}
