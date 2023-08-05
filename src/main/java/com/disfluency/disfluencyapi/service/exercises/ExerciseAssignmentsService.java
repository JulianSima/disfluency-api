package com.disfluency.disfluencyapi.service.exercises;

import com.amazonaws.HttpMethod;
import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.dto.exercises.ExercisePracticeDTO;
import com.disfluency.disfluencyapi.repository.ExerciseAssignmentsRepo;
import com.disfluency.disfluencyapi.service.aws.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExerciseAssignmentsService {

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
        String url = "/audios/" + exerciseId + LocalDateTime.now() + ".mp3";
        var preSignedUrl = s3Service.generatePreSignedUrl(url, "pf5302", HttpMethod.PUT);
        exerciseAssignment.addExercisePractice(exercisePracticeService.createExercisePractice("https://pf5302.s3.us-east-2.amazonaws.com" + url));
        log.info(exerciseAssignment.toString());
        exerciseAssignmentsRepo.save(exerciseAssignment);
        return preSignedUrl;
    }
}
