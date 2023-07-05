package com.disfluency.disfluencyapi.domain.exercises;

import com.disfluency.disfluencyapi.dto.exercises.NewExerciseDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Exercise {

    @Id
    private String id;
    private String title;
    private String instruction;
    private String phrase;
    private String sampleRecordingUrl;

    public static Exercise newExercise(NewExerciseDTO exerciseDTO) {
        return Exercise
                .builder()
                .title(exerciseDTO.title())
                .instruction(exerciseDTO.instruction())
                .phrase(exerciseDTO.phrase())
                .sampleRecordingUrl(exerciseDTO.sampleRecordingUrl())
                .build();
    }
}
