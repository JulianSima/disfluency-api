package com.disfluency.disfluencyapi.dto.forms;

import java.util.List;

public record NewFormCompletionEntryDTO(List<FormQuestionResponseDTO> responses) {
}
