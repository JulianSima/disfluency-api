package com.disfluency.disfluencyapi.dto.exercises;

import java.util.List;

public record NewExerciseAssignmentDTO(List<String> patientIds, List<String> exerciseIds) {
}
