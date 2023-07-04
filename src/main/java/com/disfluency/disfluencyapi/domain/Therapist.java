package com.disfluency.disfluencyapi.domain;

import java.util.List;

public class Therapist implements UserRole {

    private String id;
    private String name;
    private String lastName;
    private String profilePictureUrl;
    private List<Patient> patients;
    private List<Exercise> exercises;
    private List<Form> forms;
}
