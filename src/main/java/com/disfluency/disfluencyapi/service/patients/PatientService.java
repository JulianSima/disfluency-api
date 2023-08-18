package com.disfluency.disfluencyapi.service.patients;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.exception.PatientNotFoundException;
import com.disfluency.disfluencyapi.repository.PatientRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {
    
    private final PatientRepo patientRepo;

    public Patient getPatientById(String patientId) {
        return patientRepo.findById(patientId).orElseThrow( () -> new PatientNotFoundException(patientId));
    }

    public Patient createPatient(NewPatientDTO newPatient) {
        return patientRepo.save(Patient.newPatient(newPatient));
    }
}
