package com.disfluency.disfluencyapi.service.exercises;

import com.disfluency.disfluencyapi.domain.exercises.ExercisePractice;
import com.disfluency.disfluencyapi.dto.exercises.ExercisePracticeDTO;
import com.disfluency.disfluencyapi.repository.ExercisePracticeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExercisePracticeService {

    private final ExercisePracticeRepo exercisePracticeRepo;

    public ExercisePractice createExercisePractice(String recordingUrl) {
        return exercisePracticeRepo.save(ExercisePractice.newExercisePractice(recordingUrl));
    }
}