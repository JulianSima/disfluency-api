package com.disfluency.disfluencyapi.service.forms;

import com.disfluency.disfluencyapi.domain.forms.FormQuestion;
import com.disfluency.disfluencyapi.dto.forms.NewFormDTO;
import com.disfluency.disfluencyapi.dto.forms.NewFormQuestionDTO;
import com.disfluency.disfluencyapi.repository.FormQuestionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormQuestionService {

    private final FormQuestionRepo formQuestionRepo;

    public FormQuestion getFormQuestionById(String formQuestionId) {
        return formQuestionRepo.findById(formQuestionId).orElseThrow();
    }

    public List<FormQuestion> createQuestions(List<NewFormQuestionDTO> newFormQuestionDTO) {
        List<FormQuestion> questions = newFormQuestionDTO.stream()
                .map(q -> FormQuestion.newFormQuestion(q))
                .collect(Collectors.toList());

        formQuestionRepo.saveAll(questions);

        return questions;
    }
}
