package com.disfluency.disfluencyapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseDTO;
import com.disfluency.disfluencyapi.repository.ExerciseRepo;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepo exerciseRepo;

    public Exercise createExercise(NewExerciseDTO exerciseDTO) {
        return exerciseRepo.save(Exercise.newExercise(exerciseDTO));
    }
}
