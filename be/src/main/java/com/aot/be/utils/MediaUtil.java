package com.aot.be.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MediaUtil {
    private final S3Client s3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    public String uploadFile(MultipartFile file,String sub) throws Exception{
        try {
            String originalName = file.getOriginalFilename();
            String extension = "";

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }
            String key = sub + UUID.randomUUID() +extension;
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromBytes(file.getBytes()));
            return   key;
        }catch (Exception e){
            throw new RuntimeException("Cannot upload file to S3: " + e.getMessage(), e);
        }
    }

    public void deleteFile(String fileUrl){
        try {
            if(fileUrl == null || fileUrl.isEmpty()) return;

            String prefix = "https://" + bucketName + ".s3.ap-southeast-2.amazonaws.com/" ;

            if(!fileUrl.startsWith(prefix)){
                throw new RuntimeException("Invalid file url: " + fileUrl);
            }
            String key = fileUrl.substring(prefix.length());
            s3Client.deleteObject(builder -> builder
                                          .bucket(bucketName)
                                          .key(key)
                                          .build()
                                 );
        }catch (Exception e){
            throw new RuntimeException("Cannot delete file from S3: " + e.getMessage(), e);
        }
    }
}
