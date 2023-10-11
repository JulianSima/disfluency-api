package com.disfluency.disfluencyapi.dto.session;

import com.disfluency.disfluencyapi.domain.disfluency.TranscriptionWord;

import java.util.List;

public record UpdatedSessionDTO(List<TranscriptionWord> updatedAnalysis) {
}
