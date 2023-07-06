package com.disfluency.disfluencyapi.repository;

import com.disfluency.disfluencyapi.domain.users.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {

    User findOneByAccountAndPassword(String account, String password);
}
