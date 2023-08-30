package com.disfluency.disfluencyapi.service.exercises;

import com.amazonaws.HttpMethod;
import com.disfluency.disfluencyapi.domain.exercises.ExercisePractice;
import com.disfluency.disfluencyapi.dto.exercises.ExercisePracticeDTO;
import com.disfluency.disfluencyapi.repository.ExercisePracticeRepo;
import com.disfluency.disfluencyapi.service.aws.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExercisePracticeService {

    private static final Integer PRE_SIGNED_GET_EXPIRATION = 1000;

    private static final String S3_BASE_URL = "https://pf5302.s3.us-east-2.amazonaws.com/";
    private static final String S3_BUCKET = "pf5302";

    private final ExercisePracticeRepo exercisePracticeRepo;
    private final S3Service s3Service;

    public ExercisePractice createExercisePractice(String recordingUrl) {
        return exercisePracticeRepo.save(ExercisePractice.newExercisePractice(recordingUrl));
    }

    public void presignUrl(ExercisePractice exercisePractice) {
        String shortUrl = exercisePractice.getRecordingUrl().replace(S3_BASE_URL, "");
        exercisePractice.setRecordingUrl(
                s3Service.generatePreSignedUrl(shortUrl, S3_BUCKET, HttpMethod.GET, PRE_SIGNED_GET_EXPIRATION)
        );
    }
}
