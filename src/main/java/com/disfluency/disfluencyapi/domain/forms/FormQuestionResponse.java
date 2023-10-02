package com.disfluency.disfluencyapi.domain.forms;

import com.disfluency.disfluencyapi.dto.forms.FormQuestionResponseDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class FormQuestionResponse {

    @Id
    private String id;
    private FormQuestion question;
    private Integer scaleResponse;
    private String followUpResponse;

    public static FormQuestionResponse newFormQuestionResponse(FormQuestionResponseDTO response, FormQuestion question) {
        return FormQuestionResponse
                    .builder()
                    .question(question)
                    .scaleResponse(response.scaleResponse())
                    .followUpResponse(response.followUpResponse())
                    .build();
    }
}
