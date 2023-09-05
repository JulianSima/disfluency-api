package com.disfluency.disfluencyapi.domain.forms;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class FormQuestionResponse {

    @Id
    private String id;
    private FormQuestion question;
    private AnswerScale scaleResponse;
    private String followUpResponse;
}
