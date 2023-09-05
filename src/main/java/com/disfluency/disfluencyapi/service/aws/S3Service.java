package com.disfluency.disfluencyapi.service.aws;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3Client;

    public static final Integer PRE_SIGNED_UPLOAD_EXPIRATION = Integer.parseInt("${amazon.aws.uploadExpiration}");
    public static final Integer PRE_SIGNED_GET_EXPIRATION = Integer.parseInt("${amazon.aws.getExpiration}");

    public static final String S3_BASE_URL = "${amazon.aws.s3BaseUrl}";
    public static final String S3_BUCKET = "${amazon.aws.s3Bucket}";
    public static final String S3_UPLOAD_FOLDER = "${amazon.aws.s3UploadFolder}";

    public String generatePreSignedUrl(String filePath, String bucketName, HttpMethod httpMethod, int expirationMinutes) {
        var calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, expirationMinutes);
        return amazonS3Client.generatePresignedUrl(bucketName, filePath, calendar.getTime(), httpMethod).toString();
    }
}
