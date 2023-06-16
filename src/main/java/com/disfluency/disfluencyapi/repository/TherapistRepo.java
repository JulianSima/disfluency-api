package com.disfluency.disfluencyapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.disfluency.disfluencyapi.domain.Therapist;

public interface TherapistRepo extends MongoRepository<Therapist, String>{
    
}
