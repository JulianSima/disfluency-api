package com.disfluency.disfluencyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TherapistNotFoundException extends ResponseStatusException {

    public TherapistNotFoundException(String therapistId){
        super(HttpStatus.NOT_FOUND, String.format("Therapist of id '%s' does not exist", therapistId));
    }
}
