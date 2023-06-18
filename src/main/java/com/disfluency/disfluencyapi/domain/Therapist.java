package com.disfluency.disfluencyapi.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.disfluency.disfluencyapi.dto.NewTherapistDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Therapist {
    
    @Id
    private String id;
    private String name;
    private String lastName;
    @DocumentReference
    private List<Patient> patients;
    private List<Exercise> exercises;
    private List<Form> forms;

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public static Therapist newTherapist(NewTherapistDTO newTherapist) {
        return Therapist.builder()
            .name(newTherapist.name())
            .lastName(newTherapist.lastName())
            .patients(new ArrayList<>())
            .exercises(new ArrayList<>())
            .forms(new ArrayList<>())
            .build();
    }
}
