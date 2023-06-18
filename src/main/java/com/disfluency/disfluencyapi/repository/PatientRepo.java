package com.disfluency.disfluencyapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.disfluency.disfluencyapi.domain.Patient;

public interface PatientRepo extends MongoRepository<Patient, String>{
    
}
