package com.disfluency.disfluencyapi.domain.exercises;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "exerciseAssignments")
public class ExerciseAssignment {

    @Id
    private String id;
    private Exercise exercise;
    private LocalDate dateOfAssignment;
    @DocumentReference
    private List<ExercisePractice> practiceAttempts;


    public ExerciseAssignment(Exercise exercise) {
        this.exercise = exercise;
        dateOfAssignment = LocalDate.now();
        practiceAttempts = new ArrayList<>();
    }

    public void addExercisePractice(ExercisePractice exercisePractice) {
        practiceAttempts.add(exercisePractice);
    }
}
