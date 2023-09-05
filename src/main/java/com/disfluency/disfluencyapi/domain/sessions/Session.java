package com.disfluency.disfluencyapi.domain.sessions;

import com.disfluency.disfluencyapi.domain.disfluency.TranscriptionWord;
import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.dto.analysis.AnalysisResponse;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseDTO;
import com.disfluency.disfluencyapi.dto.session.NewSessionDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class Session {
    @Id
    private String id;
    private LocalDate date;
    private String recordingUrl;
    private List<TranscriptionWord> transcription;

    public static Session newSession(String audioUrl, AnalysisResponse analysisResponse) {
        return Session
                .builder()
                .recordingUrl(audioUrl)
                .date(LocalDate.now())
                .transcription(analysisResponse.getChunks())
                .build();
    }
}
