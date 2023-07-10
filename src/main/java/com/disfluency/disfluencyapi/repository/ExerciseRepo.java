package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepo extends MongoRepository<Exercise, String> {
}
