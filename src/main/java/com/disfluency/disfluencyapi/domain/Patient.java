package com.disfluency.disfluencyapi.domain;

import com.disfluency.disfluencyapi.dto.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.PatientDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
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

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public PatientDTO toDTO() {
        return new PatientDTO(id, name, lastName, getAge());
    }

    public static Patient newPatient(NewPatientDTO newPatientDTO, String therapistId) {
        return Patient.builder()
                .name(newPatientDTO.name())
                .lastName(newPatientDTO.lastName())
                .dateOfBirth(newPatientDTO.birthday())
                .exerciseAssignments(new ArrayList<>())
                .formAssignments(new ArrayList<>())
                //.therapistId(therapistId)
                .build();
    }
}
