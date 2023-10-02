package com.disfluency.disfluencyapi.dto.forms;

import java.util.List;

public record NewFormDTO(String title, List<NewFormQuestionDTO> questions) {
}
