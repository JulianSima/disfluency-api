package com.disfluency.disfluencyapi.domain.sessions;

import com.disfluency.disfluencyapi.domain.disfluency.TranscriptionWord;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Session {

    private String id;
    private LocalDate date;
    private String recordingUrl;
    private List<TranscriptionWord> transcription;
}
