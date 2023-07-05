package com.disfluency.disfluencyapi.domain.exercises;

import java.time.LocalDate;
import java.util.List;

public class ExerciseAssignment {

    private String id;
    private Exercise exercise;
    private LocalDate dateOfAssignment;
    private List<ExercisePractice> practiceAttempts;
}
