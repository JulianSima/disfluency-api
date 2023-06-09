package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.dto.patients.PatientDTO;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("patient/{patientId}")
    public PatientDTO getPatientById(@PathVariable String patientId) {
        return patientService.getPatientById(patientId).orElseThrow().toDTO();
    }

    @GetMapping("patient/{patientId}/exerciseAssignments")
    public List<ExerciseAssignment> getExerciseAssignmentsPatient(@PathVariable String patientId) {
        return patientService.getPatientById(patientId).orElseThrow().getExerciseAssignments();
    }

    @GetMapping("patient/{patientId}/formAssignments")
    public List<FormAssignment> getFormAssignmentsPatient(@PathVariable String patientId) {
        return patientService.getPatientById(patientId).orElseThrow().getFormAssignments();
    }

    @GetMapping("patient/{patientId}/therapySession")
    public List<Session> getTherapySessionPatient(@PathVariable String patientId) {
        return patientService.getPatientById(patientId).orElseThrow().getTherapySession();
    }
}
