package com.disfluency.disfluencyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExistingAccountException  extends ResponseStatusException {

    public ExistingAccountException(String username) {
        super(HttpStatus.BAD_REQUEST, "The user " + username + " already exists in the system");
    }
}
