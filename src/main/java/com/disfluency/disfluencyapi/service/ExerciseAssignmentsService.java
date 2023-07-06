package com.disfluency.disfluencyapi.service;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.dto.exercises.ExercisePracticeDTO;
import com.disfluency.disfluencyapi.repository.ExerciseAssignmentsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseAssignmentsService {

    private final ExerciseAssignmentsRepo exerciseAssignmentsRepo;
    private final ExercisePracticeService exercisePracticeService;

    public ExerciseAssignment createExerciseAssignments(Exercise exercise){
        return exerciseAssignmentsRepo.save(new ExerciseAssignment(exercise));
    }

    public Optional<ExerciseAssignment> getExerciseAssignmentById(String exerciseId) {
        return exerciseAssignmentsRepo.findById(exerciseId);
    }

    public void createExercisePractice(String exerciseId, ExercisePracticeDTO exercisePracticeDTO) {
        var exerciseAssignment = exerciseAssignmentsRepo.findById(exerciseId).orElseThrow();
        exerciseAssignment.addExercisePractice(exercisePracticeService.createExercisePractice(exercisePracticeDTO));
        exerciseAssignmentsRepo.save(exerciseAssignment);
    }
}
