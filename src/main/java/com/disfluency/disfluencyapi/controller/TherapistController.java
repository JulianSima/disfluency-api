package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseDTO;
import com.disfluency.disfluencyapi.dto.forms.NewFormAssignmentDTO;
import com.disfluency.disfluencyapi.dto.forms.NewFormDTO;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.patients.PatientDTO;
import com.disfluency.disfluencyapi.dto.patients.PreSignedUrlDTO;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.dto.therapists.TherapistDTO;
import com.disfluency.disfluencyapi.service.exercises.ExerciseAssignmentService;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import com.disfluency.disfluencyapi.service.therapists.TherapistService;
import com.disfluency.disfluencyapi.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TherapistController {
    
    private final TherapistService therapistService;
    private final UserService userService;
    private final PatientService patientService;

    @GetMapping("/therapists/{therapistId}")
    public TherapistDTO getTherapistById(@PathVariable String therapistId) {
        var therapist = therapistService.getTherapistById(therapistId);
        return therapist.toDTO();
    }

    @PostMapping(value = "/therapists/{therapistId}/patients", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PatientDTO createPatient(@RequestBody NewPatientDTO newPatient, @PathVariable String therapistId) {
        log.info(newPatient.toString());
        therapistService.validateExistingTherapist(therapistId);
        return userService.createPatientForTherapist(newPatient, therapistId).toDTO();
    }
    
    @GetMapping("/therapists/{therapistId}/patients")
    public List<PatientDTO> getPatientsByTherapistId(@PathVariable String therapistId) {
        log.info("Retrieving patients of therapist: " + therapistId);
        return therapistService.getPatientsByTherapistId(therapistId)
                .stream()
                .map(patientService::presignPatientUrls)
                .map(Patient::toDTO)
                .toList();
    }

    @PostMapping(value = "/therapists/{therapistId}/forms", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Form createForm(@RequestBody NewFormDTO newForm, @PathVariable String therapistId) {
        log.info(newForm.toString());
        therapistService.validateExistingTherapist(therapistId);
        return therapistService.createFormForTherapist(therapistId, newForm);
    }

    @GetMapping(value = "/therapists/{therapistId}/forms")
    public List<Form> getFormsFromTherapist(@PathVariable String therapistId) {
        log.info(String.format("Forms by '%s' therapist", therapistId));
        therapistService.validateExistingTherapist(therapistId);
        var therapist = therapistService.getTherapistById(therapistId);
        return therapist.getForms();
    }

    @GetMapping(value = "/therapist/{therapistId}/exercises/presigned")
    public PreSignedUrlDTO getExerciseSamplePreSignedUrl(@PathVariable String therapistId){
        log.info("Generating pre-signed url for new exercise of therapist: " + therapistId);
        return therapistService.getPreSignedUrl(therapistId);
    }

    @PostMapping(value = "/therapist/{therapistId}/exercises", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Exercise createExercise(@RequestBody NewExerciseDTO newExercise, @PathVariable String therapistId) {
        log.info("Creating new exercise '"+ newExercise.title() + "' of therapist: " + therapistId);
        return therapistService.createExerciseForTherapist(therapistId, newExercise);
    }

    @GetMapping(value = "/therapist/{therapistId}/exercises")
    public List<Exercise> getExercises(@PathVariable String therapistId) {
        log.info("Getting excersises of therapist: " + therapistId);
        return therapistService.getExercisesByTherapistId(therapistId);
    }
}
