package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.forms.FormQuestionResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormQuestionResponseRepo extends MongoRepository<FormQuestionResponse, String> {
}
