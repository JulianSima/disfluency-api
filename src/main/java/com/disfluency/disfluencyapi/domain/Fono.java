package com.disfluency.disfluencyapi.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.disfluency.disfluencyapi.dto.NewFonoDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fono {
    
    @Id
    private String id;
    private String name;
    private String lastName;
    @DocumentReference
    private List<Paciente> pacientes;
    private List<Ejercicio> ejercicios;
    private List<Cuestionario> cuestionarios;

    public void addPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public static Fono newFono(NewFonoDTO newFono) {
        return Fono.builder()
            .name(newFono.name())
            .lastName(newFono.lastName())
            .pacientes(new ArrayList<>())
            .ejercicios(new ArrayList<>())
            .cuestionarios(new ArrayList<>())
            .build();
    }
}
