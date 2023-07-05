package com.disfluency.disfluencyapi.dto.patient;

import com.disfluency.disfluencyapi.domain.ExerciseAssignment;

import java.time.LocalDate;
import java.util.List;

public record PatientDTO(String name, String lastName, LocalDate dateOfBirth, String id, String email,
                         LocalDate joinedSince, Integer profilePic, String weeklyTurn, String weeklyHour,
                         List<ExerciseAssignment> exercises) {
}
