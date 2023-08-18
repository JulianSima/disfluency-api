package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.tokens.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends MongoRepository<RefreshToken, String> {

    Optional<RefreshToken> findByToken(String token);

    int deleteByUserId(String userId);
}
