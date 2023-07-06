package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.exercises.ExercisePractice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExercisePracticeRepo extends MongoRepository<ExercisePractice, String> {
}
