package com.disfluency.disfluencyapi.service.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${amazon.aws.accessKeyId}")
    private String accessKeyId;
    @Value("${amazon.aws.accessKeySecret}")
    private String accessKeySecret;
    @Value("${amazon.aws.region}")
    private String regionName;

    @Bean
    public AmazonS3 getAmazonS3Client() {
        System.out.println(accessKeyId);
        System.out.println(accessKeySecret);
        var awsCredentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(regionName)
                .build();
    }
}
