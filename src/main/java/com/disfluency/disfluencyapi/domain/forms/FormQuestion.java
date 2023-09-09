package com.disfluency.disfluencyapi.domain.forms;

import com.disfluency.disfluencyapi.dto.forms.NewFormQuestionDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class FormQuestion {

    @Id
    private String id;
    private String scaleQuestion;
    private String followUpQuestion;
    private AnswerScale majorScale;
    private AnswerScale minorScale;

    public static FormQuestion newFormQuestion(NewFormQuestionDTO newFormQuestion) {
        return FormQuestion
                .builder()
                .scaleQuestion(newFormQuestion.scaleQuestion())
                .followUpQuestion(newFormQuestion.followUpQuestion())
                .majorScale(new AnswerScale(newFormQuestion.majorScale(), 1))
                .minorScale(new AnswerScale(newFormQuestion.minorScale(), 5))
                .build();
    }
}
