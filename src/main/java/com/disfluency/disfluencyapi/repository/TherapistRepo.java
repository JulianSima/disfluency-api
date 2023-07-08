package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TherapistRepo extends MongoRepository<Therapist, String>{
    
}
