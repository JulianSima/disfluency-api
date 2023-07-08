package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepo extends MongoRepository<Patient, String>{
    
}
