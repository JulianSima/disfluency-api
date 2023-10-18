package com.disfluency.disfluencyapi.domain.analysis;

import com.disfluency.disfluencyapi.domain.disfluency.TranscriptionWord;
import com.disfluency.disfluencyapi.dto.analysis.AnalysisResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class Analysis {
    @Id
    private String id;
    private LocalDate date;
    private String recordingUrl;
    private List<TranscriptionWord> transcription;

    public static Analysis newAnalysis(String audioUrl, AnalysisResponse analysisResponse) {
        return Analysis
                .builder()
                .recordingUrl(audioUrl)
                .date(LocalDate.now())
                .transcription(analysisResponse.getChunks())
                .build();
    }

    public void updatedAnalysis(List<TranscriptionWord> transcription) {
        this.transcription = transcription;
    }
}
