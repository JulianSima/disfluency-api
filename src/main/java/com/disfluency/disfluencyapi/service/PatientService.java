package com.disfluency.disfluencyapi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.disfluency.disfluencyapi.domain.Patient;
import com.disfluency.disfluencyapi.dto.patient.NewPatientDTO;
import com.disfluency.disfluencyapi.repository.PatientRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {
    
    private final PatientRepo patientRepo;

    public Optional<Patient> getPatientById(String patientId) {
        return patientRepo.findById(patientId);
    }

    public Patient createPatient(NewPatientDTO newPatient, String therapistId) {
        return patientRepo.save(Patient.newPatient(newPatient, therapistId));
    }
}
