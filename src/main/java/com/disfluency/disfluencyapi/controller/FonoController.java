package com.disfluency.disfluencyapi.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.disfluency.disfluencyapi.domain.Fono;
import com.disfluency.disfluencyapi.domain.Paciente;
import com.disfluency.disfluencyapi.dto.NewFonoDTO;
import com.disfluency.disfluencyapi.dto.NewPacienteDTO;
import com.disfluency.disfluencyapi.dto.PacienteDTO;
import com.disfluency.disfluencyapi.service.FonoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FonoController {
    
    private final FonoService fonoService;
    
    @GetMapping("/fonos/{fonoId}/pacientes")
    public List<PacienteDTO> getPacientesByFonoId(@PathVariable String fonoId) {
        return fonoService.getPacientesByFonoId(fonoId)
            .stream()
            .map(p -> p.toDTO())
            .toList();
    }

    @PostMapping(value = "/fonos/{fonoId}/pacientes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Paciente createPaciente(@RequestBody NewPacienteDTO newPaciente, @PathVariable String fonoId) {
        return fonoService.createPacienteForFono(newPaciente, fonoId);
    }

    @GetMapping("/fonos/{fonoId}")
    public Fono getFonoById(@PathVariable String fonoId) {
        return fonoService.getFonoById(fonoId);
    }

    @PostMapping(value = "/fonos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Fono createFono(@RequestBody NewFonoDTO newFono) {
        return fonoService.createFono(newFono);
    }
}
