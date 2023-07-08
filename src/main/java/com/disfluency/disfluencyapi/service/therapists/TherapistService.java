package com.disfluency.disfluencyapi.service.therapists;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseAssignmentDTO;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseDTO;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.repository.TherapistRepo;
import com.disfluency.disfluencyapi.service.exercises.ExerciseService;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import com.disfluency.disfluencyapi.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TherapistService {
    
    private final TherapistRepo therapistRepo;
    private final PatientService patientService;
    private final ExerciseService exerciseService;
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
        return therapistRepo.findById(therapistId).orElseThrow().getPatients();
    }

    public Patient createPatientForTherapist(NewPatientDTO newPatient, String therapistId) {
        var patient = patientService.createPatient(newPatient, therapistId);
        userService.createUser(newPatient.email(), "12345678", patient);  //TODO mandar mail
        var therapist = therapistRepo.findById(therapistId).orElseThrow();
        therapist.addPatient(patient);
        therapistRepo.save(therapist);
        return patient;
    }

    public Exercise createExerciseForTherapist(NewExerciseDTO newExercise, String therapistId) {
        var exercise = exerciseService.createExercise(newExercise);
        var therapist = therapistRepo.findById(therapistId).orElseThrow();
        therapist.addExercise(exercise);
        therapistRepo.save(therapist);
        return exercise;
    }

    public List<Exercise> getExercisesByTherapistId(String therapistId) {
        return therapistRepo.findById(therapistId).orElseThrow().getExercises();
    }

    public void createExercisesAssignment(NewExerciseAssignmentDTO assignment, String therapistId) {
        var therapist = therapistRepo.findById(therapistId).orElseThrow();
        List<Exercise> exercises = therapist.getExercisesWithIds(assignment.exerciseIds());
        List<Patient> patients = therapist.getPatientsWithIds(assignment.patientsIds());
        patients.forEach(patient -> patientService.exercisesAssignments(patient.getId(), exercises));
    }
}
