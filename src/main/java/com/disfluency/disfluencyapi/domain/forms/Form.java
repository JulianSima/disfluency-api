package com.disfluency.disfluencyapi.domain.forms;

import com.disfluency.disfluencyapi.dto.forms.NewFormDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Form {

    @Id
    private String id;
    private String title;
    @DocumentReference
    private List<FormQuestion> questions;

    public static Form newForm(NewFormDTO newForm) {
        return Form
                .builder()
                .title(newForm.title())
                .questions(new ArrayList<>())
                .build();
    }

    public void addQuestions(List<FormQuestion> questions) {
        this.questions = questions;
    }
}
