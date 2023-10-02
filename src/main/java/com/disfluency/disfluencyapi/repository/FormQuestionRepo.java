package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.forms.FormQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormQuestionRepo extends MongoRepository<FormQuestion, String> {
}
