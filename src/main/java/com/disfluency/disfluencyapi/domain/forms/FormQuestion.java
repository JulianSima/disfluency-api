package com.disfluency.disfluencyapi.domain.forms;

import com.disfluency.disfluencyapi.dto.forms.NewFormQuestionDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.List;

@Data
@Builder
public class FormQuestion {

    @Id
    private String id;
    private String scaleQuestion;
    private String followUpQuestion;
    private List<AnswerScale> answerScales;

    public static FormQuestion newFormQuestion(NewFormQuestionDTO newFormQuestion) {
        return FormQuestion
                .builder()
                .scaleQuestion(newFormQuestion.scaleQuestion())
                .followUpQuestion(newFormQuestion.followUpQuestion())
                .answerScales(List.of(AnswerScale.NO_ME_CUESTA, AnswerScale.ME_CUESTA_POCO, AnswerScale.ME_CUESTA))
                .build();
    }
}
