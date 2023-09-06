package com.disfluency.disfluencyapi.dto.forms;

import com.disfluency.disfluencyapi.domain.forms.AnswerScale;

public record FormQuestionResponseDTO(String idQuestion, AnswerScale scaleResponse, String followUpResponse) {
}
