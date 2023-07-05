package com.disfluency.disfluencyapi.domain;

import java.time.LocalDate;
import java.util.List;

public class Session {

    private String id;
    private LocalDate date;
    private String recordingUrl;
    private List<TranscriptionWord> transcription;
}
