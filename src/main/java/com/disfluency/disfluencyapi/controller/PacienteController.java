package com.disfluency.disfluencyapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.disfluency.disfluencyapi.dto.PatientDTO;
import com.disfluency.disfluencyapi.service.PatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PacienteController {

    private final PatientService pacienteService;

    @GetMapping("pacientes/{pacienteId}")
    public PatientDTO getPacienteById(@PathVariable String pacienteId) {
        return pacienteService.getPatientById(pacienteId).orElseThrow().toDTO();
    }
}
