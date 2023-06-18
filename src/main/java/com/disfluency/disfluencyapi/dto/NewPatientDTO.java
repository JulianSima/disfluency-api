package com.disfluency.disfluencyapi.dto;

import java.time.LocalDate;

public record NewPatientDTO(String name, String lastName, LocalDate birthday) {
    
}
