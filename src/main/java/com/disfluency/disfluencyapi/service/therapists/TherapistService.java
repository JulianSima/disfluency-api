package com.disfluency.disfluencyapi.service.therapists;

import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import com.disfluency.disfluencyapi.dto.forms.NewFormDTO;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.exception.UserNotFoundException;
import com.disfluency.disfluencyapi.exception.TherapistNotFoundException;
import com.disfluency.disfluencyapi.repository.TherapistRepo;
import com.disfluency.disfluencyapi.service.forms.FormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TherapistService {
    
    private final TherapistRepo therapistRepo;
    private final FormService formService;

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
}
