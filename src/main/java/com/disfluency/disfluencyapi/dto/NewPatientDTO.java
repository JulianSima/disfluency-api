package com.disfluency.disfluencyapi.dto;

import java.time.LocalDate;

public record NewPatientDTO(String name, String lastName, String email, LocalDate birthday, String weeklyTurn,
                            String weeklyHour) {
}
