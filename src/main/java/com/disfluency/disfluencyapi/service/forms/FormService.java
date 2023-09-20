package com.disfluency.disfluencyapi.service.forms;

import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.dto.forms.NewFormDTO;
import com.disfluency.disfluencyapi.exception.FormNotFoundException;
import com.disfluency.disfluencyapi.repository.FormRepo;
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
}
