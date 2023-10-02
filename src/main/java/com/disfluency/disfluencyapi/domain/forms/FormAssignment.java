package com.disfluency.disfluencyapi.domain.forms;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class FormAssignment {

    @Id
    private String id;
    private Form form;
    private LocalDate date;
    @DocumentReference
    private List<FormCompletionEntry> completionEntries;

    public static FormAssignment newFormAssignment(Form form){
        return FormAssignment.builder()
                .form(form)
                .date(LocalDate.now())
                .completionEntries(new ArrayList<>())
                .build();
    }

    public void addCompletionEntry(FormCompletionEntry completionEntry) {
        this.completionEntries.add(completionEntry);
    }

}
