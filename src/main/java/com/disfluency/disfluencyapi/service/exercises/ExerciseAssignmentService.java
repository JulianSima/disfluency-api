package com.disfluency.disfluencyapi.service.exercises;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.dto.exercises.ExercisePracticeDTO;
import com.disfluency.disfluencyapi.repository.ExerciseAssignmentsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExerciseAssignmentService {

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
        log.info(exerciseAssignment.toString());
        exerciseAssignmentsRepo.save(exerciseAssignment);
    }
}