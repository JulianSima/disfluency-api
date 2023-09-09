package com.disfluency.disfluencyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FormNotFoundException extends ResponseStatusException {
    public FormNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, String.format("The form with ID '%s' does not exist", id));
    }
}
