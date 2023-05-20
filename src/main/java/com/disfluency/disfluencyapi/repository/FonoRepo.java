package com.disfluency.disfluencyapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.disfluency.disfluencyapi.domain.Fono;

public interface FonoRepo extends MongoRepository<Fono, String>{
    
}
