package com.disfluency.disfluencyapi.dto.patients;

import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record PatientDTO(String name, String lastName, LocalDate dateOfBirth, String id, String email,
                         LocalDate joinedSince, Integer profilePictureUrl, List<DayOfWeek> weeklyTurn, LocalTime weeklyHour,
                         List<ExerciseAssignment> exercises, List<FormAssignment> forms) {
}
