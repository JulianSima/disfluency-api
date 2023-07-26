package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.users.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findOneByAccount(String account);
}
