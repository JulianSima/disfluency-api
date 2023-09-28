package com.disfluency.disfluencyapi.service.aws;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3Client;

    //TODO usar properties para obtener valores
    public static Integer PRE_SIGNED_UPLOAD_EXPIRATION = 10;
    public static Integer PRE_SIGNED_GET_EXPIRATION = 1000;
    public static String S3_BASE_URL = "https://pf5302.s3.us-east-2.amazonaws.com/";
    public static String S3_BUCKET = "pf5302";
    public static String S3_UPLOAD_FOLDER = "audios/";

    public String generatePreSignedUrl(String filePath, String bucketName, HttpMethod httpMethod, int expirationMinutes) {
        var calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, expirationMinutes);
        return amazonS3Client.generatePresignedUrl(bucketName, filePath, calendar.getTime(), httpMethod).toString();
    }

    /**
     * Given a pre signed url, returns the original url to the s3 file
     * @param url pre signed url
     * @return base url of the given pre signed url
     */
    public String reversePreSignedUrl(String url) {
        //removes pre-signed suffix and reformats ':' characters
        return url.substring(0, url.indexOf("?")).replace("%3A", ":");
    }
}
