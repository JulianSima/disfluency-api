package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.exercises.ExercisePractice;
import com.disfluency.disfluencyapi.dto.exercises.ExercisePracticeDTO;
import com.disfluency.disfluencyapi.service.exercises.ExerciseAssignmentsService;
import com.disfluency.disfluencyapi.service.exercises.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseAssignmentsService exerciseAssignmentService;

    @GetMapping("exercises/{exerciseId}")
    public Exercise getExerciseById(@PathVariable String exerciseId) {
        return exerciseService.getExerciseById(exerciseId).orElseThrow();
    }

    @GetMapping("exercisesAssignments/{exerciseId}")
    public ExerciseAssignment getExerciseAssignmentById(@PathVariable String exerciseId) {
        return exerciseAssignmentService.getExerciseAssignmentById(exerciseId).orElseThrow();
    }

    @PostMapping(value = "exercisesAssignments/{exerciseId}/practices", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createExercisePractice(@RequestBody ExercisePracticeDTO exercisePracticeDTO, @PathVariable String exerciseId) {
        exerciseAssignmentService.createExercisePractice(exerciseId, exercisePracticeDTO);
    }

    @GetMapping("exercisesAssignments/{exerciseId}/practices")
    public List<ExercisePractice> getPracticeAttemptsByExerciseAssignmentId(@PathVariable String exerciseId) {
        return exerciseAssignmentService.getExerciseAssignmentById(exerciseId).orElseThrow().getPracticeAttempts();
    }
}
