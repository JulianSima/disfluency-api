package com.disfluency.disfluencyapi.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.disfluency.disfluencyapi.dto.NewPacienteDTO;
import com.disfluency.disfluencyapi.dto.PacienteDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Paciente {
    
    @Id
    private String id;
    private String name;
    private String lastName;
    private LocalDate birthday;
    private List<Ejercicio> ejercicios;
    private List<Cuestionario> cuestionarios;
    private String fonoId;

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public PacienteDTO toDTO() {
        return new PacienteDTO(id, name, lastName, getAge());
    }

    public static Paciente newPaciente(NewPacienteDTO newPacienteDTO, String fonoId) {
        return Paciente.builder()
            .name(newPacienteDTO.name())
            .lastName(newPacienteDTO.lastName())
            .birthday(newPacienteDTO.birthday())
            .ejercicios(new ArrayList<>())
            .cuestionarios(new ArrayList<>())
            .fonoId(fonoId)
            .build();
    }
}
