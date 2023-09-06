package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.forms.FormCompletionEntry;
import com.disfluency.disfluencyapi.dto.forms.NewFormCompletionEntryDTO;
import com.disfluency.disfluencyapi.service.forms.FormAssignmentService;
import com.disfluency.disfluencyapi.service.forms.FormQuestionResponseService;
import com.disfluency.disfluencyapi.service.forms.FormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FormController {

    private final FormService formService;
    private final FormAssignmentService formAssignmentService;
    private final FormQuestionResponseService formQuestionResponseService;


    @GetMapping("forms/{formId}")
    public Form getFormById(@PathVariable String formId) {
        return formService.getFormById(formId).orElseThrow();
    }

    @GetMapping("formAssignments/{formAssignmentId}")
    public List<FormCompletionEntry> getCompletionEntriesByFormAssignmentId(@PathVariable String formAssignmentId) {
        return formAssignmentService.getCompletionEntriesByFormAssignmentId(formAssignmentId);
    }

    @PostMapping("formAssignments/{formAssignmentId}/formCompletionEntries")
    public List<FormCompletionEntry> completeFormAssignment(@RequestBody NewFormCompletionEntryDTO formCompletionEntryDTO, @PathVariable String formAssignmentId) {
        var responses = formCompletionEntryDTO.responses()
                .stream().map( response -> formQuestionResponseService.createFormQuestionResponse(response) );
        return formAssignmentService.completeFormAssignment(formAssignmentId);
    }
}
