package com.disfluency.disfluencyapi.service;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.repository.ExerciseAssignmentsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseAssignmentsService {

    private final ExerciseAssignmentsRepo exerciseAssignmentsRepo;

    public ExerciseAssignment createExerciseAssignments(Exercise exercise){
        return exerciseAssignmentsRepo.save(new ExerciseAssignment(exercise));
    }
}
