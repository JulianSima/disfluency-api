package com.disfluency.disfluencyapi.domain.disfluency;

import lombok.Data;

import java.util.List;
@Data
public class TranscriptionWord {

    private String text;
    private WordTimestamp timestamp;
    private List<Disfluency> disfluency;
}
