package com.disfluency.disfluencyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SaltException extends ResponseStatusException {

    public SaltException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Hash creation failed");
    }
}
