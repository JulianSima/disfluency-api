package com.disfluency.disfluencyapi.domain.therapists;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.users.UserRole;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.dto.therapists.TherapistDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonTypeName("therapist")
public class Therapist implements UserRole {

    @Id
    private String id;
    private String name;
    private String lastName;
    private int profilePictureUrl;
    @DocumentReference
    @JsonIgnore
    private List<Patient> patients;
    @DocumentReference
    @JsonIgnore
    private List<Exercise> exercises;
    @DocumentReference
    private List<Form> forms;

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addExercise(Exercise exercise) { exercises.add(exercise); }

    public static Therapist newTherapist(NewTherapistDTO newTherapist) {
        return Therapist.builder()
                .name(newTherapist.name())
                .lastName(newTherapist.lastName())
                .profilePictureUrl(newTherapist.profilePictureUrl()) //TODO revisar cuando no se usen los avatares
                .patients(new ArrayList<>())
                .exercises(new ArrayList<>())
                .forms(new ArrayList<>())
                .build();
    }

    public TherapistDTO toDTO() {
        return new TherapistDTO(id, name, lastName, Integer.valueOf(profilePictureUrl));
    }

    public List<Exercise> getExercisesWithIds(List<String> exercisesIds) {
        return exercises.stream().filter( exercise ->  exercisesIds.contains(exercise.getId()) ).toList();
    }

    public List<Patient> getPatientsWithIds(List<String> patientsIds) {
        return patients.stream().filter(patient -> patientsIds.contains(patient.getId()) ).toList();
    }

    @Override
    public UserRoleDTO toUserRoleDTO() {
        return new UserRoleDTO("Therapist", this);
    }

    public void addForm(Form form) {
        this.forms.add(form);
    }
}
