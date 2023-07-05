package com.disfluency.disfluencyapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.disfluency.disfluencyapi.dto.PatientDTO;
import com.disfluency.disfluencyapi.service.PatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("patient/{patientId}")
    public PatientDTO getPatientById(@PathVariable String patientId) {
        return patientService.getPatientById(patientId).orElseThrow().toDTO();
    }
}
