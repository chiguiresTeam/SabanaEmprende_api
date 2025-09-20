package com.hackaton.SabanaEmprende_api.Common.Components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class S3Config {

    @Value("${r2.url}") private String url;
    @Value("${r2.region}") private String region;
    @Value("${r2.access-key}") private String accessKey;
    @Value("${r2.secret-key}") private String secretKey;
    @Value("${r2.force-path-style:false}") private boolean forcePathStyle;

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials creds = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Client.builder()
                .endpointOverride(URI.create(url))
                .credentialsProvider(StaticCredentialsProvider.create(creds))
                .region(Region.of(region))          // “auto” para R2
                .serviceConfiguration(
                        S3Configuration.builder()
                                .pathStyleAccessEnabled(forcePathStyle)
                                .build())
                .build();
    }

}
