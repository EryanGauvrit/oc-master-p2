package com.p3openclassrooms.p3oc.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileService {

    private final S3Service s3Service;
    private final List<String> allowedMimeTypes = List.of("image/jpeg", "image/png", "image/jpg");

    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty !");
        }
        if (!allowedMimeTypes.contains(file.getContentType())) {
            throw new IllegalArgumentException("File type not allowed !");
        }
        
        return s3Service.uploadFile(file);
    }
}
