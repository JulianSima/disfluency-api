package com.disfluency.disfluencyapi.dto.patients;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record PatientSimpleDTO(String name, String lastName, LocalDate dateOfBirth, String id, String email,
                               LocalDate joinedSince, Integer profilePictureUrl, List<DayOfWeek> weeklyTurn, LocalTime weeklyHour) {
}
