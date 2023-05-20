package com.disfluency.disfluencyapi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.disfluency.disfluencyapi.domain.Paciente;
import com.disfluency.disfluencyapi.dto.NewPacienteDTO;
import com.disfluency.disfluencyapi.repository.PacienteRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {
    
    private final PacienteRepo pacienteRepo;

    public Optional<Paciente> getPacienteById(String pacienteId) {
        return pacienteRepo.findById(pacienteId);
    }

    public Paciente createPaciente(NewPacienteDTO newPaciente, String fonoId) {
        return pacienteRepo.save(Paciente.newPaciente(newPaciente, fonoId));
    }
}
