package com.example.assignment.controller;

import com.example.assignment.model.User;
import com.example.assignment.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class UploadlerController {

    @Autowired
    private UploadService uploadService;

    @GetMapping("/importfile")
    public String index() {
        return "view/Upload";
    }

    @PostMapping("/uploaded_csv")
    public String uploadCsvToFirestore(@RequestParam("file") MultipartFile file, Model model) throws IOException, ExecutionException, InterruptedException {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
        } else {

            System.out.println("step 1");
            uploadService.saveCsv(file);
            uploadService.getCsvFirestore();
            uploadService.saveCsvtoDB();
         List<User> users= uploadService.getCsvfromDB();
            model.addAttribute("users", users);
            model.addAttribute("status", true);
        }
        return "view/CsvView";
    }
}
