package com.disfluency.disfluencyapi.domain.exercises;

import com.disfluency.disfluencyapi.dto.exercises.ExercisePracticeDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "practiceAttempts")
public class ExercisePractice {

    @Id
    private String id;
    private LocalDate date;
    private String recordingUrl;

    public static ExercisePractice newExercisePractice(String recordingUrl) {
        return builder().date(LocalDate.now()).recordingUrl(recordingUrl).build();
    }
}
