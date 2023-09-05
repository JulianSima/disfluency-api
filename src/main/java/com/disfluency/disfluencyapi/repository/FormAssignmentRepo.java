package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormAssignmentRepo extends MongoRepository<FormAssignment, String> {
}
