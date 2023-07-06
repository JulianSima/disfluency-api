package com.disfluency.disfluencyapi.domain.forms;

import java.time.LocalDate;
import java.util.List;

public class FormAssignment {

    private String id;
    private Form form;
    private LocalDate date;
    private List<FormCompletionEntry> completionEntries;
}
