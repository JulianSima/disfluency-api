package com.disfluency.disfluencyapi.domain.patients;

import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.domain.sessions.SessionTurn;
import com.disfluency.disfluencyapi.domain.users.UserRole;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.patients.PatientDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "patients")
@JsonTypeName("patient")
public class Patient implements UserRole {

    @Id
    private String id;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private int profilePictureUrl;
    @JsonUnwrapped
    private SessionTurn sessionTurn;
    private String email;

    private LocalDate joinedSince;

    @JsonIgnore
    private List<Session> therapySession;
    @JsonIgnore
    @DocumentReference
    private List<ExerciseAssignment> exerciseAssignments;
    @JsonIgnore
    private List<FormAssignment> formAssignments;

    @JsonIgnore
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public PatientDTO toDTO() {
        return new PatientDTO(name, lastName, dateOfBirth, id, email, joinedSince,
                profilePictureUrl, sessionTurn.getWeeklyTurn(), sessionTurn.getWeeklyHour(), exerciseAssignments);
    }

    public static Patient newPatient(NewPatientDTO newPatientDTO, String therapistId) {
        return Patient.builder()
                .name(newPatientDTO.name())
                .lastName(newPatientDTO.lastName())
                .dateOfBirth(newPatientDTO.dateOfBirth())
                .joinedSince(LocalDate.now())
                .profilePictureUrl(newPatientDTO.profilePictureUrl())
                .sessionTurn(new SessionTurn(newPatientDTO.weeklyTurn(),newPatientDTO.weeklyHour()))
                .therapySession(new ArrayList<>())
                .exerciseAssignments(new ArrayList<>())
                .formAssignments(new ArrayList<>())
                .email(newPatientDTO.email())
                //.therapistId(therapistId) //TODO revisar si es necesario tener el id del terapeuta
                .build();
    }

    public void addExercisesAssignment(List<ExerciseAssignment> exerciseAssignments) {
        this.exerciseAssignments.addAll(exerciseAssignments);
    }

    @Override
    public UserRoleDTO toUserRoleDTO() {
        return new UserRoleDTO("Patient", this);
    }
}
