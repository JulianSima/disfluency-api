package com.disfluency.disfluencyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PatientNotFoundException extends ResponseStatusException {
    public PatientNotFoundException(String patientId) {
        super(HttpStatus.NOT_FOUND, String.format("Patient of id '%s' does not exist", patientId));
    }
}
