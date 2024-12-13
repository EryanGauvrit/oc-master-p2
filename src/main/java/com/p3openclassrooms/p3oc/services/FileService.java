package com.p3openclassrooms.p3oc.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty !");
        }
        
        return "upload/" + file.getOriginalFilename();
    }
}
