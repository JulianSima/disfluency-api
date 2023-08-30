package com.disfluency.disfluencyapi.dto.analysis;

import com.disfluency.disfluencyapi.domain.disfluency.TranscriptionWord;
import lombok.Data;

import java.util.List;

@Data
public class AnalysisResponse {

    private List<TranscriptionWord> chunks;
    private String text;
}
