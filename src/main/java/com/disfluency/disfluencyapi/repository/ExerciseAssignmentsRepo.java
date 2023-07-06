package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseAssignmentsRepo extends MongoRepository<ExerciseAssignment, String> {
}
