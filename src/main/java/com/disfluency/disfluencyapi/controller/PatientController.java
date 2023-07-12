package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.dto.patients.PatientDTO;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("patient/{patientId}")
    public PatientDTO getPatientById(@PathVariable String patientId) {
        return patientService.getPatientById(patientId).orElseThrow().toDTO();
    }
}
