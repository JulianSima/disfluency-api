package com.disfluency.disfluencyapi.dto;

import java.time.LocalDate;

public record NewPacienteDTO(String name, String lastName, LocalDate birthday) {
    
}
