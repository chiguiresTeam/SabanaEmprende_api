package com.hackaton.SabanaEmprende_api.Common.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class R2StorageService {

    private final S3Client s3Client;
    private final String bucket;
    private final String publicUrlBase;

    public R2StorageService(
            S3Client s3Client,
            @Value("${r2.name}") String bucket,
            @Value("${r2.public-endpoint}") String publicUrlBase) {

        this.s3Client = s3Client;
        this.bucket = bucket;
        // quita barra final si la trae
        this.publicUrlBase = publicUrlBase.endsWith("/")
                ? publicUrlBase.substring(0, publicUrlBase.length() - 1)
                : publicUrlBase;
    }

    public String upload(MultipartFile file, String newName) throws IOException {
        String key = UUID.randomUUID() + "-" + newName;
        PutObjectRequest putReq = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(file.getContentType())
                .acl(ObjectCannedACL.PUBLIC_READ) // opcional: deja el objeto público
                .build();
        s3Client.putObject(putReq, RequestBody.fromBytes(file.getBytes()));
        return buildFileUrl(key);
    }

    public List<String> listFiles() {
        ListObjectsV2Response resp = s3Client.listObjectsV2(
                ListObjectsV2Request.builder().bucket(bucket).build());
        return resp.contents().stream()
                .map(S3Object::key)
                .map(this::buildFileUrl)
                .collect(Collectors.toList());
    }

    public void delete(String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket).key(key).build());
    }

    private String buildFileUrl(String key) {
        return publicUrlBase + "/" + key;
    }
}
