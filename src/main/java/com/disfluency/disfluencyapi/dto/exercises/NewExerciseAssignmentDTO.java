package com.disfluency.disfluencyapi.dto.exercises;

import java.util.List;

public record NewExerciseAssignmentDTO(List<String> patientsIds, List<String> exerciseIds) {
}
