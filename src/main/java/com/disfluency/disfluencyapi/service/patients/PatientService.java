package com.disfluency.disfluencyapi.service.patients;

import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.state.PatientUserState;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.exception.PatientNotFoundException;
import com.disfluency.disfluencyapi.repository.PatientRepo;
import com.disfluency.disfluencyapi.service.exercises.ExerciseAssignmentService;
import com.disfluency.disfluencyapi.service.forms.FormAssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {
    
    private final PatientRepo patientRepo;
    private final ExerciseAssignmentService exerciseAssignmentService;
    private final FormAssignmentService formAssignmentService;

    public Patient getPatientById(String patientId) {
        return patientRepo.findById(patientId).orElseThrow( () -> new PatientNotFoundException(patientId));
    }

    public Patient createPatient(NewPatientDTO newPatient) {
        return patientRepo.save(Patient.newPatient(newPatient));
    }

    public Patient presignPatientUrls(Patient patient) {
        patient.getExerciseAssignments().forEach(exerciseAssignmentService::presignExerciseUrls);
        return patient;
    }
  
    public Patient confirmPatient(Patient patient){
        patient.setState(PatientUserState.ACTIVE);
        return patientRepo.save(patient);
    }

    public void formAssignments(String patientId, List<Form> forms) {
        var patient = getPatientById(patientId);

        List<FormAssignment> formAssignments = forms.stream()
                .map(formAssignmentService::createExerciseAssignments)
                .toList();
        patient.addFormsAssignment(formAssignments);
        patientRepo.save(patient);
//        try{
//            notificationService.sendCommonMessage("A trabajar", "Tu terapeuta te ha asignado ejercicios para practicar.", patient.getFcmToken());
//        }catch (Exception e){
//            log.error("Error while notificating: {}",e.toString());
//        }
    }
}
