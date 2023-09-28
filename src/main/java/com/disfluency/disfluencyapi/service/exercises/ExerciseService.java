package com.disfluency.disfluencyapi.service.exercises;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseAssignmentDTO;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseDTO;
import com.disfluency.disfluencyapi.repository.ExerciseRepo;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExerciseService {

    private final ExerciseRepo exerciseRepo;

    public Exercise createExercise(NewExerciseDTO exerciseDTO) {
        return exerciseRepo.save(Exercise.newExercise(exerciseDTO));
    }

    public Optional<Exercise> getExerciseById(String exerciseId) {
        return exerciseRepo.findById(exerciseId);
    }

    public List<Exercise> getExercisesByIdList(List<String> ids){
        return exerciseRepo.findAllById(ids);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepo.findAll();
    }

    public void assignExercisesToPatients(NewExerciseAssignmentDTO assignment, final PatientService patientService) {
        log.info("assigning exercises {} to patients {}", assignment.exerciseIds(), assignment.patientIds());

        List<Exercise> exercises = getExercisesByIdList(assignment.exerciseIds());
        List<Patient> patients = patientService.getPatientsByIdList(assignment.patientIds());
        patients.forEach(patient -> patientService.assignExercisesToPatient(patient.getId(), exercises));
    }
}