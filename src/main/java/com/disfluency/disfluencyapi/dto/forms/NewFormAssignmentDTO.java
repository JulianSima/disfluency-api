package com.disfluency.disfluencyapi.dto.forms;

import java.util.List;

public record NewFormAssignmentDTO(List<String> patientIds, List<String> formIds) {
}
