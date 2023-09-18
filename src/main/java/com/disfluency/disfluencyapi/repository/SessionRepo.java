package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.sessions.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepo extends MongoRepository<Session, String> {
}
