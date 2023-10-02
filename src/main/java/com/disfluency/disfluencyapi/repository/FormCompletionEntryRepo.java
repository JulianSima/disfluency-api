package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.forms.FormCompletionEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormCompletionEntryRepo extends MongoRepository<FormCompletionEntry, String> {
}
