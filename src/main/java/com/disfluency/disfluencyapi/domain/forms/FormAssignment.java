package com.disfluency.disfluencyapi.domain.forms;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
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


}
