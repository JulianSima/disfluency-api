package com.disfluency.disfluencyapi.service.therapists;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.exception.TherapistNotFoundException;
import com.disfluency.disfluencyapi.repository.TherapistRepo;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import com.disfluency.disfluencyapi.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TherapistService {
    
    private final TherapistRepo therapistRepo;
    private final PatientService patientService;
    private final UserService userService;

    public Therapist createTherapist(NewTherapistDTO newTherapist) {
        var therapist = Therapist.newTherapist(newTherapist);
        return therapistRepo.save(therapist);
    }

    public Therapist getTherapistById(String therapistId) {
        return therapistRepo.findById(therapistId).orElseThrow();
    }

    public List<Therapist> getAllTherapist() {
        return this.therapistRepo.findAll();
    }

    public List<Patient> getPatientsByTherapistId(String therapistId) {
        return therapistRepo.findById(therapistId).orElseThrow(() -> new TherapistNotFoundException(therapistId)).getPatients();
    }

    public Patient createPatientForTherapist(NewPatientDTO newPatient, String therapistId) {
        var therapist = therapistRepo.findById(therapistId).orElseThrow(() -> new TherapistNotFoundException(therapistId));
        var patient = patientService.createPatient(newPatient, therapistId);
        userService.createUser(newPatient.email(), "12345678", patient);  //TODO mandar mail
        therapist.addPatient(patient);
        therapistRepo.save(therapist);
        return patient;
    }
}
