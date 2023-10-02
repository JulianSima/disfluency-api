package com.disfluency.disfluencyapi.domain.exercises;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "exerciseAssignments")
public class ExerciseAssignment {

    @Id
    private String id;
    private Exercise exercise;
    private LocalDate dateOfAssignment;
    @DocumentReference
    private List<ExercisePractice> practiceAttempts;

    public static ExerciseAssignment newExerciseAssignment(Exercise exercise) {
        return ExerciseAssignment.builder()
                .exercise(exercise)
                .dateOfAssignment(LocalDate.now())
                .practiceAttempts(new ArrayList<>())
                .build();
    }

    public void addExercisePractice(ExercisePractice exercisePractice) {
        practiceAttempts.add(exercisePractice);
    }
}
