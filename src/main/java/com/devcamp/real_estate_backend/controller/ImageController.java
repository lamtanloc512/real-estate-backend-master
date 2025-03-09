package com.devcamp.real_estate_backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@RestController
@RequestMapping("/api/v1/images")
@CrossOrigin
public class ImageController {
    private final AmazonS3 amazonS3;
    private final String BUCKET_NAME = "devcamp-upload-bucket";

    @Autowired
    public ImageController(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('ADMIN','SALE','CUSTOMER')")
    public ResponseEntity<List<Map<String, String>>> uploadImages(@RequestParam("files") MultipartFile[] files) {
        List<Map<String, String>> uploadedFiles = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

                // Metadata setup
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());
                metadata.setContentType(file.getContentType());

                // Upload file
                amazonS3.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file.getInputStream(), metadata));

                // Generate file URL
                String fileUrl = amazonS3.getUrl(BUCKET_NAME, fileName).toString();

                // Add file details to the response
                uploadedFiles.add(Map.of("fileName", fileName, "url", fileUrl));
            }
            return ResponseEntity.ok(uploadedFiles);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(List.of(Map.of("error", "Error uploading files: " + e.getMessage())));
        }
    }

}
