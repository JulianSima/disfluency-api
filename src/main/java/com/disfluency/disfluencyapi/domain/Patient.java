package com.disfluency.disfluencyapi.domain;

import com.disfluency.disfluencyapi.dto.patient.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.patient.PatientDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "patients")
public class Patient implements UserRole {
    //TOOD: falta User
    @Id
    private String id;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String profilePictureUrl;
    private SessionTurn sessionTurn;

    private LocalDate joinedSince;

    private List<Session> therapySession;
    private List<ExerciseAssignment> exerciseAssignments;
    private List<FormAssignment> formAssignments;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public PatientDTO toDTO() {
        //TODO validar DTO en mobile
        return new PatientDTO(name, lastName, dateOfBirth, id, "abc@gmail.com", joinedSince,
                Integer.valueOf(profilePictureUrl), sessionTurn.getDays(), sessionTurn.getTime(), exerciseAssignments);
    }

    public static Patient newPatient(NewPatientDTO newPatientDTO, String therapistId) {
        return Patient.builder()
                .name(newPatientDTO.name())
                .lastName(newPatientDTO.lastName())
                .dateOfBirth(newPatientDTO.birthday())
                .joinedSince(LocalDate.now())
                .profilePictureUrl("1") //TODO preguntar como dejar esto para mobile
                .sessionTurn(new SessionTurn(newPatientDTO.weeklyTurn(),newPatientDTO.weeklyHour()))
                .therapySession(new ArrayList<>())
                .exerciseAssignments(new ArrayList<>())
                .formAssignments(new ArrayList<>())
                //.therapistId(therapistId) //TODO revisar si es necesario tener el id del terapeuta
                .build();
    }
}
