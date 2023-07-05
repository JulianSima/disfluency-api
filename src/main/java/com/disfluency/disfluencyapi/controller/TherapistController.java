package com.disfluency.disfluencyapi.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.disfluency.disfluencyapi.domain.Therapist;
import com.disfluency.disfluencyapi.domain.Patient;
import com.disfluency.disfluencyapi.dto.NewTherapistDTO;
import com.disfluency.disfluencyapi.dto.patient.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.patient.PatientDTO;
import com.disfluency.disfluencyapi.service.TherapistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TherapistController {
    
    private final TherapistService therapistService;
    
    @GetMapping("/therapists/{therapistId}/patients")
    public List<PatientDTO> getPatientsByTherapistId(@PathVariable String therapistId) {
        return therapistService.getPatientsByTherapistId(therapistId)
            .stream()
            .map(p -> p.toDTO())
            .toList();
    }

    @PostMapping(value = "/therapists/{therapistId}/patients", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Patient createPatient(@RequestBody NewPatientDTO newPatient, @PathVariable String therapistId) {
        return therapistService.createPatientForTherapist(newPatient, therapistId);
    }

    @GetMapping("/therapists/{therapistId}")
    public Therapist getTherapistById(@PathVariable String therapistId) {
        return therapistService.getTherapistById(therapistId);
    }

    @PostMapping(value = "/therapists", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Therapist createTherapist(@RequestBody NewTherapistDTO newTherapist) {
        return therapistService.createTherapist(newTherapist);
    }

    @GetMapping("/therapists")
    public List<Therapist> getAllTherapist() {
        return therapistService.getAllTherapist();
    }
}
