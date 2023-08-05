package com.disfluency.disfluencyapi.controller;

import com.amazonaws.HttpMethod;
import com.disfluency.disfluencyapi.service.aws.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping("/presigned-url")
    public String generatePreSignedUrl() {
        return s3Service.generatePreSignedUrl(UUID.randomUUID().toString() + ".txt", "pf5302", HttpMethod.PUT);
    }
}
