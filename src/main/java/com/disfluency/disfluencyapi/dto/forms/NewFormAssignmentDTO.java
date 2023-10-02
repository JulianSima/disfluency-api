package com.disfluency.disfluencyapi.dto.forms;

import java.util.List;

public record NewFormAssignmentDTO(List<String> patientsIds, List<String> formIds) {
}
