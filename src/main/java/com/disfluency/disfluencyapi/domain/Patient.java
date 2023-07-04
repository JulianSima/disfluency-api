package com.disfluency.disfluencyapi.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Patient implements UserRole {
    //TOOD: falta User
    private String id;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String profilePictureUrl;
    private SessionTurn sessionTurn;

    private List<Session> therapySession;
    private List<ExerciseAssignment> exerciseAssignments;
    private List<FormAssignment> formAssignments;
}
