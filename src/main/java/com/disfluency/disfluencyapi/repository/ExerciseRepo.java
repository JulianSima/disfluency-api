package com.disfluency.disfluencyapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;

public interface ExerciseRepo extends MongoRepository<Exercise, String> {
}
