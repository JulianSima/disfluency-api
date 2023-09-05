package com.disfluency.disfluencyapi.service.patients;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.session.NewSessionDTO;
import com.disfluency.disfluencyapi.exception.PatientNotFoundException;
import com.disfluency.disfluencyapi.repository.PatientRepo;
import com.disfluency.disfluencyapi.service.analysis.AnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {
    
    private final PatientRepo patientRepo;
    private final AnalysisService analysisService;

    public Patient getPatientById(String patientId) {
        return patientRepo.findById(patientId).orElseThrow( () -> new PatientNotFoundException(patientId));
    }

    public Patient createPatient(NewPatientDTO newPatient) {
        return patientRepo.save(Patient.newPatient(newPatient));
    }

    public Session createTherapySessionForPatient(NewSessionDTO newSession, String patientId) {
        var patient = getPatientById(patientId);
        var session = analysisService.createAnalysedSession(newSession.recordingUrl());
        patient.addTherapySession(session);
        patientRepo.save(patient);
        return session;
    }

    public List<Session> getTherapySessionsForPatient(String patientId) {
        var patient = getPatientById(patientId);
        return patient.getTherapySession();
    }
}
