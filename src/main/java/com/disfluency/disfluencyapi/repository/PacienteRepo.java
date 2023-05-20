package com.disfluency.disfluencyapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.disfluency.disfluencyapi.domain.Paciente;

public interface PacienteRepo extends MongoRepository<Paciente, String>{
    
}
