package com.disfluency.disfluencyapi.service.forms;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.dto.forms.NewFormAssignmentDTO;
import com.disfluency.disfluencyapi.dto.forms.NewFormDTO;
import com.disfluency.disfluencyapi.exception.FormNotFoundException;
import com.disfluency.disfluencyapi.repository.FormRepo;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormService {

    private final FormRepo formRepo;
    private final FormQuestionService formQuestionService;

    public Form createForm(NewFormDTO newForm) {
        var questions = formQuestionService.createQuestions(newForm.questions());
        var form = Form.newForm(newForm);
        form.addQuestions(questions);
        formRepo.save(form);
        return form;
    }

    public Form getFormById(String formId) {
        return formRepo.findById(formId).orElseThrow( () ->new FormNotFoundException(formId));
    }

    public List<Form> getAllForms() {
        return formRepo.findAll();
    }

    public List<Form> getFormsByIdList(List<String> ids){
        return formRepo.findAllById(ids);
    }

    public void assignFormsToPatients(NewFormAssignmentDTO assignment, final PatientService patientService) {
        log.info("assigning forms {} to patients {}", assignment.formIds(), assignment.patientIds());

        List<Form> forms = getFormsByIdList(assignment.formIds());
        List<Patient> patients = patientService.getPatientsByIdList(assignment.patientIds());
        patients.forEach(patient -> patientService.formAssignments(patient.getId(), forms));
    }
}
