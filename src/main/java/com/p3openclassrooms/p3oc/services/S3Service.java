package com.p3openclassrooms.p3oc.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    final String bucketName = "openclassrooms-p2-rental-picture";
    final S3Client s3Client = s3Client();
    
    private static S3Client s3Client() {
        Region region = Region.EU_WEST_3;   
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create("AKIAQUFLQQSZCKU2NP4F", "GFk2Cn0T47h8JlS7+LY3405LGyRrIBHdq+du4sVO");
        return S3Client.builder()
                       .region(region)
                       .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                       .httpClient(ApacheHttpClient.builder().build())
                       .build();
    }

    public String uploadFile(MultipartFile file) {
        String mimeType = file.getContentType();
        String key = UUID.randomUUID() + "." + mimeType.split("/")[1];
        Path tempFile = Paths.get(System.getProperty("java.io.tmpdir"), key);
        try {
            Files.write(tempFile, file.getBytes());
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                                                                .bucket(bucketName)
                                                                .key(key)
                                                                .build();
            s3Client.putObject(objectRequest, RequestBody.fromFile(tempFile));
            // Files.delete(tempFile);
            return getPublicUrl(key);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    private String getPublicUrl(String key) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, Region.EU_WEST_3.id(), key);
    }
}
