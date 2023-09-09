package com.disfluency.disfluencyapi.service.forms;

import com.disfluency.disfluencyapi.domain.forms.AnswerScale;
import com.disfluency.disfluencyapi.domain.forms.FormQuestion;
import com.disfluency.disfluencyapi.domain.forms.FormQuestionResponse;
import com.disfluency.disfluencyapi.dto.forms.FormQuestionResponseDTO;
import com.disfluency.disfluencyapi.repository.FormQuestionResponseRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormQuestionResponseService {

    private FormQuestionResponseRepo formQuestionResponseRepo;

    private final FormQuestionService formQuestionService;

    public FormQuestionResponse createFormQuestionResponse(FormQuestionResponseDTO response) {
        var question = formQuestionService.getFormQuestionById(response.idQuestion());
        var formQuestionResponse = FormQuestionResponse.newFormQuestionResponse(response, question);
        return formQuestionResponseRepo.save(formQuestionResponse);
    }
}
