package com.disfluency.disfluencyapi.domain.exercises;

import com.disfluency.disfluencyapi.domain.analysis.Analysis;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "practiceAttempts")
public class ExercisePractice {

    @Id
    private String id;
    private LocalDateTime date;
    private String recordingUrl;
    private Analysis analysis;

    public static ExercisePractice newExercisePractice(String recordingUrl) {
        return builder()
                .date(LocalDateTime.now())
                .recordingUrl(recordingUrl)
                .analysis(null)
                .build();
    }
}
