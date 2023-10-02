package com.disfluency.disfluencyapi.service.therapists;

import com.amazonaws.HttpMethod;
import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseDTO;
import com.disfluency.disfluencyapi.dto.forms.NewFormAssignmentDTO;
import com.disfluency.disfluencyapi.dto.forms.NewFormDTO;
import com.disfluency.disfluencyapi.dto.patients.PreSignedUrlDTO;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.exception.UserNotFoundException;
import com.disfluency.disfluencyapi.exception.TherapistNotFoundException;
import com.disfluency.disfluencyapi.repository.TherapistRepo;
import com.disfluency.disfluencyapi.service.aws.S3Service;
import com.disfluency.disfluencyapi.service.exercises.ExerciseService;
import com.disfluency.disfluencyapi.service.forms.FormService;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.disfluency.disfluencyapi.service.aws.S3Service.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TherapistService {
    
    private final TherapistRepo therapistRepo;
    private final FormService formService;
    private final PatientService patientService;
    private final ExerciseService exerciseService;
    private final S3Service s3Service;

    public Therapist createTherapist(NewTherapistDTO newTherapist) {
        var therapist = Therapist.newTherapist(newTherapist);
        return therapistRepo.save(therapist);
    }

    public Therapist getTherapistById(String therapistId) {
        return therapistRepo.findById(therapistId).orElseThrow(() -> new TherapistNotFoundException(therapistId));
    }

    public List<Therapist> getAllTherapist() {
        return this.therapistRepo.findAll();
    }

    public List<Patient> getPatientsByTherapistId(String therapistId) {
        return therapistRepo.findById(therapistId).orElseThrow(() -> new TherapistNotFoundException(therapistId)).getPatients();
    }

    public void validateExistingTherapist(String therapistId) {
        if(!isAnExistingTherapist(therapistId)) {
            throw new UserNotFoundException(therapistId);
        }
    }

    private Boolean isAnExistingTherapist(String therapistId) {
        return therapistRepo.existsById(therapistId);
    }


    public void addPatientToTherapist(String therapistId, Patient patient) {
        var therapist = getTherapistById(therapistId);
        therapist.addPatient(patient);
        therapistRepo.save(therapist);
    }



    public Form createFormForTherapist(String therapistId, NewFormDTO newForm) {
        var therapist = getTherapistById(therapistId);
        var form = formService.createForm(newForm);
        therapist.addForm(form);
        therapistRepo.save(therapist);
        return form;
    }

    public void createFormAssignment(NewFormAssignmentDTO assignment, String therapistId) {
        var therapist = getTherapistById(therapistId);
        log.info(therapist.toString());
        List<Form> forms = therapist.getFormsWithIds(assignment.formIds());
        List<Patient> patients = therapist.getPatientsWithIds(assignment.patientsIds());
        patients.forEach(patient -> patientService.formAssignments(patient.getId(), forms));
    }

    public Exercise createExerciseForTherapist(String therapistId, NewExerciseDTO newExercise) {
        var therapist = getTherapistById(therapistId);

        var sampleUrl = s3Service.reversePreSignedUrl(newExercise.sampleRecordingUrl());
        var exercise = exerciseService.createExercise(new NewExerciseDTO(newExercise.title(), newExercise.instruction(), newExercise.phrase(), sampleUrl));

        therapist.addExercise(exercise);
        therapistRepo.save(therapist);
        return exercise;
    }

    public PreSignedUrlDTO getPreSignedUrl(String therapistId){
        String newUrl = S3_UPLOAD_FOLDER + therapistId + "/exercise/" + LocalDateTime.now() + ".mp3";
        return new PreSignedUrlDTO(s3Service.generatePreSignedUrl(newUrl, S3_BUCKET, HttpMethod.PUT, PRE_SIGNED_UPLOAD_EXPIRATION));
    }

}
