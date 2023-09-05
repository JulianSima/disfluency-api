package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.dto.patients.PatientDTO;
import com.disfluency.disfluencyapi.dto.session.NewSessionDTO;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("patient/{patientId}")
    public PatientDTO getPatientById(@PathVariable String patientId) {
        return patientService.getPatientById(patientId).toDTO();
    }

    @GetMapping("patient/{patientId}/exerciseAssignments")
    public List<ExerciseAssignment> getExerciseAssignmentsPatient(@PathVariable String patientId) {
        var patient = patientService.getPatientById(patientId);
        return patientService.presignPatientUrls(patient).getExerciseAssignments();
    }

    @PostMapping(value = "patient/{patientId}/sessions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Session createTherapySession(@RequestBody NewSessionDTO newSession, @PathVariable String patientId) {
        return patientService.createTherapySessionForPatient(newSession, patientId);
    }

    @GetMapping("patient/{patientId}/sessions")
    public List<Session> getTherapySessions(@PathVariable String patientId) {
        return patientService.getTherapySessionsForPatient(patientId);
    }
}
