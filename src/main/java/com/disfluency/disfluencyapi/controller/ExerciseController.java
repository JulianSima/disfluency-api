package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.exercises.ExercisePractice;
import com.disfluency.disfluencyapi.dto.exercises.ExercisePracticeDTO;
import com.disfluency.disfluencyapi.service.exercises.ExerciseAssignmentService;
import com.disfluency.disfluencyapi.service.exercises.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseAssignmentService exerciseAssignmentService;

    @GetMapping("exercises/{exerciseId}")
    public Exercise getExerciseById(@PathVariable String exerciseId) {
        return exerciseService.getExerciseById(exerciseId).orElseThrow();
    }

    @GetMapping("exercisesAssignments/{exerciseId}")
    public ExerciseAssignment getExerciseAssignmentById(@PathVariable String exerciseId) {
        return exerciseAssignmentService.getExerciseAssignmentById(exerciseId).orElseThrow();
    }

    // TODO
    @PostMapping(value = "exercisesAssignments/{exerciseId}/practices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExercisePracticeDTO createExercisePractice(@PathVariable String exerciseId) {
        return new ExercisePracticeDTO(exerciseAssignmentService.createExercisePractice(exerciseId));
    }

    @GetMapping("exercisesAssignments/{exerciseId}/practices")
    public List<ExercisePractice> getPracticeAttemptsByExerciseAssignmentId(@PathVariable String exerciseId) {
        return exerciseAssignmentService.getExerciseAssignmentById(exerciseId).orElseThrow().getPracticeAttempts();
    }
}