package com.disfluency.disfluencyapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.disfluency.disfluencyapi.dto.PacienteDTO;
import com.disfluency.disfluencyapi.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping("pacientes/{pacienteId}")
    public PacienteDTO getPacienteById(@PathVariable String pacienteId) {
        return pacienteService.getPacienteById(pacienteId).orElseThrow().toDTO();
    }
}
