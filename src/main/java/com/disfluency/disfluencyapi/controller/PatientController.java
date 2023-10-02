package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import com.disfluency.disfluencyapi.dto.patients.PatientDTO;
import com.disfluency.disfluencyapi.dto.patients.PreSignedUrlDTO;
import com.disfluency.disfluencyapi.dto.session.NewSessionDTO;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PatientController {

    private final PatientService patientService;

    @GetMapping("patient/{patientId}")
    public PatientDTO getPatientById(@PathVariable String patientId) {
        return patientService.getPatientById(patientId).toDTO();
    }

    @GetMapping("patient/{patientId}/exerciseAssignments")
    public List<ExerciseAssignment> getExerciseAssignmentsByPatient(@PathVariable String patientId) {
        var patient = patientService.getPatientById(patientId);
        return patientService.presignPatientUrls(patient).getExerciseAssignments();
    }

    @GetMapping("patient/{patientId}/formAssignments")
    public List<FormAssignment> getFormAssignmentsByPatient(@PathVariable String patientId) {
        var patient = patientService.getPatientById(patientId);
        return patient.getFormAssignments();
    }

    @PostMapping(value = "patient/{patientId}/sessions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Session createTherapySession(@RequestBody NewSessionDTO newSession, @PathVariable String patientId) {
        log.info("session: " + newSession + " | " + patientId);
        return patientService.createTherapySessionForPatient(newSession, patientId);
    }

    @GetMapping("patient/{patientId}/sessions")
    public List<Session> getTherapySessions(@PathVariable String patientId) {
        return patientService.getTherapySessionsForPatient(patientId);
    }

    @GetMapping("patient/{patientId}/presignedUrl")
    public PreSignedUrlDTO getPresignedUrl(@PathVariable String patientId) {
        return patientService.getPreSignedUrl(patientId);
    }
}
