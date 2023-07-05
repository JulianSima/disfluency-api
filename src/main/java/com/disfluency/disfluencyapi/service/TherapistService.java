package com.disfluency.disfluencyapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.disfluency.disfluencyapi.domain.Therapist;
import com.disfluency.disfluencyapi.domain.Patient;
import com.disfluency.disfluencyapi.dto.NewTherapistDTO;
import com.disfluency.disfluencyapi.dto.NewPatientDTO;
import com.disfluency.disfluencyapi.repository.TherapistRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TherapistService {
    
    private final TherapistRepo therapistRepo;
    private final PatientService patientService;

    public List<Patient> getPatientsByTherapistId(String therapistId) {
        return therapistRepo.findById(therapistId).orElseThrow().getPatients();
    }

    public Therapist getTherapistById(String therapistId) {
        return therapistRepo.findById(therapistId).orElseThrow();
    }

    public Therapist createTherapist(NewTherapistDTO newTherapist) {
        var therapist = Therapist.newTherapist(newTherapist);
        return therapistRepo.save(therapist);
    }

    public Patient createPatientForTherapist(NewPatientDTO newPatient, String therapistId) {
        var patient = patientService.createPatient(newPatient, therapistId);
        var therapist = therapistRepo.findById(therapistId).orElseThrow();
        therapist.addPatient(patient);
        therapistRepo.save(therapist);
        return patient;
    }

    public List<Therapist> getAllTherapist() {
        return this.therapistRepo.findAll();
    }
}
