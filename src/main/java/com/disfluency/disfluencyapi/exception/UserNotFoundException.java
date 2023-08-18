package com.disfluency.disfluencyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(String account) {
        super(HttpStatus.NOT_FOUND, String.format("Invalid user or password for account '%s'", account));
    }
}
