package com.disfluency.disfluencyapi.service.patients;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.repository.PatientRepo;
import com.disfluency.disfluencyapi.service.exercises.ExerciseAssignmentsService;
import com.disfluency.disfluencyapi.service.notifications.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {
    
    private final PatientRepo patientRepo;

    private final ExerciseAssignmentsService exerciseAssignmentsService;
    private final NotificationService notificationService;
    public Optional<Patient> getPatientById(String patientId) {
        return patientRepo.findById(patientId);
    }

    public Patient createPatient(NewPatientDTO newPatient, String therapistId) {
        return patientRepo.save(Patient.newPatient(newPatient, therapistId));
    }

    public void exercisesAssignments(String patientId, List<Exercise> exercises) {
        var patient = getPatientById(patientId).orElseThrow();
        List<ExerciseAssignment> exerciseAssignments = exercises.stream()
                .map(exerciseAssignmentsService::createExerciseAssignments)
                .toList();
        patient.addExercisesAssignment(exerciseAssignments);
        patientRepo.save(patient);
        try{
            notificationService.sendCommonMessage("A trabajar", "Tu terapeuta te ha asignado ejercicios para practicar.", patient.getFcmToken());
        }catch (Exception e){
            log.error("Error while notificating: {}",e.toString());
        }
    }
}
