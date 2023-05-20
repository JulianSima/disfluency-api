package com.disfluency.disfluencyapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.disfluency.disfluencyapi.domain.Fono;
import com.disfluency.disfluencyapi.domain.Paciente;
import com.disfluency.disfluencyapi.dto.NewFonoDTO;
import com.disfluency.disfluencyapi.dto.NewPacienteDTO;
import com.disfluency.disfluencyapi.repository.FonoRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FonoService {
    
    private final FonoRepo fonoRepo;
    private final PacienteService pacienteService;

    public List<Paciente> getPacientesByFonoId(String fonoId) {
        return fonoRepo.findById(fonoId).orElseThrow().getPacientes();
    }

    public Fono getFonoById(String fonoId) {
        return fonoRepo.findById(fonoId).orElseThrow();
    }

    public Fono createFono(NewFonoDTO newFono) {
        var fono = Fono.newFono(newFono);
        return fonoRepo.save(fono);
    }

    public Paciente createPacienteForFono(NewPacienteDTO newPaciente, String fonoId) {
        var paciente = pacienteService.createPaciente(newPaciente, fonoId);
        var fono = fonoRepo.findById(fonoId).orElseThrow();
        fono.addPaciente(paciente);
        fonoRepo.save(fono);
        return paciente;
    }
}
