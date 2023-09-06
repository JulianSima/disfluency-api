package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
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
        return patientService.getPatientById(patientId).toDTO();
    }

    @GetMapping("patient/{patientId}/exerciseAssignments")
    public List<ExerciseAssignment> getExerciseAssignmentsPatient(@PathVariable String patientId) {
        var patient = patientService.getPatientById(patientId);
        return patientService.presignPatientUrls(patient).getExerciseAssignments();
    }

    @GetMapping("patient/{patientId}/formAssignments")
    public List<FormAssignment> getFormAssignmentsPatient(@PathVariable String patientId) {
        var patient = patientService.getPatientById(patientId);
        return patient.getFormAssignments();
    }
}
