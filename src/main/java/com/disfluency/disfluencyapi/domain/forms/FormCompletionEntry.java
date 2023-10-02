package com.disfluency.disfluencyapi.domain.forms;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class FormCompletionEntry {

    @Id
    private String id;
    private LocalDate date;
    @DocumentReference
    private List<FormQuestionResponse> responses;

    public static FormCompletionEntry newFormCompletionEntry(List<FormQuestionResponse> responses) {
        return FormCompletionEntry
                    .builder()
                    .date(LocalDate.now())
                    .responses(responses)
                    .build();
    }
}
