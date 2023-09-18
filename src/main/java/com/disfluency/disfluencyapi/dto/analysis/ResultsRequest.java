package com.disfluency.disfluencyapi.dto.analysis;

import com.disfluency.disfluencyapi.domain.disfluency.TranscriptionWord;

import java.util.List;

public record ResultsRequest(String text, List<TranscriptionWord> chunks) {
}
