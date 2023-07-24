package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.patients.PatientDTO;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.dto.therapists.TherapistDTO;
import com.disfluency.disfluencyapi.service.therapists.TherapistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TherapistController {
    
    private final TherapistService therapistService;

    @PostMapping(value = "/therapists", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Therapist createTherapist(@RequestBody NewTherapistDTO newTherapist) {
        return therapistService.createTherapist(newTherapist);
    }

    @GetMapping("/therapists/{therapistId}")
    public TherapistDTO getTherapistById(@PathVariable String therapistId) {
        return therapistService.getTherapistById(therapistId).toDTO();
    }

    @GetMapping("/therapists")
    public List<Therapist> getAllTherapist() {
        return therapistService.getAllTherapist();
    }

    @PostMapping(value = "/therapists/{therapistId}/patients", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PatientDTO createPatient(@RequestBody NewPatientDTO newPatient, @PathVariable String therapistId) {
        log.info(newPatient.toString());
        return therapistService.createPatientForTherapist(newPatient, therapistId).toDTO();
    }
    
    @GetMapping("/therapists/{therapistId}/patients")
    public List<PatientDTO> getPatientsByTherapistId(@PathVariable String therapistId) {
        log.info("Retrieving patients of therapist: " + therapistId);
        return therapistService.getPatientsByTherapistId(therapistId)
            .stream()
            .map(Patient::toDTO)
            .toList();
    }
}
