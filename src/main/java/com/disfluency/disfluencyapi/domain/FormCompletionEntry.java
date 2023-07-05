package com.disfluency.disfluencyapi.domain;

import java.time.LocalDate;
import java.util.List;

public class FormCompletionEntry {

    private String id;
    private LocalDate date;
    private List<FormQuestionResponse> responses;
}
