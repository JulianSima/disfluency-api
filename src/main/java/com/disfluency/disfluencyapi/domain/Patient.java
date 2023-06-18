package com.disfluency.disfluencyapi.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.disfluency.disfluencyapi.dto.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.PatientDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Patient {
    
    @Id
    private String id;
    private String name;
    private String lastName;
    private LocalDate birthday;
    private List<Exercise> exercises;
    private List<Form> forms;
    private String therapistId;

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public PatientDTO toDTO() {
        return new PatientDTO(id, name, lastName, getAge());
    }

    public static Patient newPatient(NewPatientDTO newPatientDTO, String therapistId) {
        return Patient.builder()
            .name(newPatientDTO.name())
            .lastName(newPatientDTO.lastName())
            .birthday(newPatientDTO.birthday())
            .exercises(new ArrayList<>())
            .forms(new ArrayList<>())
            .therapistId(therapistId)
            .build();
    }
}
