package com.disfluency.disfluencyapi.dto.patients;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record NewPatientDTO(String name, String lastName, String email, LocalDate dateOfBirth, List<DayOfWeek> weeklyTurn,
                            LocalTime weeklyHour) {
}
