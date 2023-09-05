package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.forms.Form;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormRepo extends MongoRepository<Form, String> {
}
