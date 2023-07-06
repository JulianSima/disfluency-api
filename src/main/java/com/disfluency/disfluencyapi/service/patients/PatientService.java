package com.disfluency.disfluencyapi.service.patients;

import java.util.List;
import java.util.Optional;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.service.exercises.ExerciseAssignmentsService;
import org.springframework.stereotype.Service;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.repository.PatientRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {
    
    private final PatientRepo patientRepo;

    private final ExerciseAssignmentsService exerciseAssignmentsService;

    public Optional<Patient> getPatientById(String patientId) {
        return patientRepo.findById(patientId);
    }

    public Patient createPatient(NewPatientDTO newPatient, String therapistId) {
        return patientRepo.save(Patient.newPatient(newPatient, therapistId));
    }

    public void exercisesAssignments(String patientId, List<Exercise> exercises) {
        var patient = getPatientById(patientId).orElseThrow();
        List<ExerciseAssignment> exerciseAssignments = exercises.stream()
                .map(exercise -> exerciseAssignmentsService.createExerciseAssignments(exercise))
                .toList();
        patient.addExercisesAssignment(exerciseAssignments);
        patientRepo.save(patient);
    }
}
