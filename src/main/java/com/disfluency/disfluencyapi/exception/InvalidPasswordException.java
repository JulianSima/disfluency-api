package com.disfluency.disfluencyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPasswordException extends ResponseStatusException {
    public InvalidPasswordException() {
        super(HttpStatus.UNAUTHORIZED, "Invalid user or password");
    }
}
