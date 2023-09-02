package com.disfluency.disfluencyapi.service.exercises;

import com.amazonaws.HttpMethod;
import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.repository.ExerciseAssignmentsRepo;
import com.disfluency.disfluencyapi.service.aws.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExerciseAssignmentService {

    private static final Integer PRE_SIGNED_UPLOAD_EXPIRATION = 10;
    private static final Integer PRE_SIGNED_GET_EXPIRATION = 1000;

    private static final String S3_BASE_URL = "https://pf5302.s3.us-east-2.amazonaws.com/";
    private static final String S3_BUCKET = "pf5302";
    private static final String S3_UPLOAD_FOLDER = "audios/";

    private final ExerciseAssignmentsRepo exerciseAssignmentsRepo;
    private final ExercisePracticeService exercisePracticeService;
    private final S3Service s3Service;

    public ExerciseAssignment createExerciseAssignments(Exercise exercise){
        return exerciseAssignmentsRepo.save(new ExerciseAssignment(exercise));
    }

    public Optional<ExerciseAssignment> getExerciseAssignmentById(String exerciseId) {
        return exerciseAssignmentsRepo.findById(exerciseId);
    }

    public String createExercisePractice(String exerciseId) {
        var exerciseAssignment = exerciseAssignmentsRepo.findById(exerciseId).orElseThrow();
        String url = S3_UPLOAD_FOLDER + exerciseId + LocalDateTime.now() + ".mp3";
        var preSignedUrl = s3Service.generatePreSignedUrl(url, S3_BUCKET, HttpMethod.PUT, PRE_SIGNED_UPLOAD_EXPIRATION);
        log.info(preSignedUrl);

        exerciseAssignment.addExercisePractice(exercisePracticeService.createExercisePractice(S3_BASE_URL + url));
        log.info(exerciseAssignment.toString());
        exerciseAssignmentsRepo.save(exerciseAssignment);
        return preSignedUrl;
    }

    public String getPreSignedAudioUrl(String url){
        String shortUrl = url.replace(S3_BASE_URL, "");
        return s3Service.generatePreSignedUrl(shortUrl, S3_BUCKET, HttpMethod.GET, PRE_SIGNED_GET_EXPIRATION);
    }

    public void presignExerciseUrls(ExerciseAssignment exerciseAssignment) {
        exerciseAssignment.getPracticeAttempts().forEach(exercisePracticeService::presignUrl);
    }
}