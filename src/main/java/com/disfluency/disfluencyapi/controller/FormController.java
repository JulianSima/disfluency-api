package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import com.disfluency.disfluencyapi.domain.forms.FormCompletionEntry;
import com.disfluency.disfluencyapi.domain.forms.FormQuestionResponse;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseAssignmentDTO;
import com.disfluency.disfluencyapi.dto.forms.NewFormAssignmentDTO;
import com.disfluency.disfluencyapi.dto.forms.NewFormCompletionEntryDTO;
import com.disfluency.disfluencyapi.service.forms.FormAssignmentService;
import com.disfluency.disfluencyapi.service.forms.FormCompletionEntryService;
import com.disfluency.disfluencyapi.service.forms.FormQuestionResponseService;
import com.disfluency.disfluencyapi.service.forms.FormService;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FormController {

    private final FormService formService;
    private final FormAssignmentService formAssignmentService;
    private final FormQuestionResponseService formQuestionResponseService;
    private final FormCompletionEntryService formCompletionEntryService;
    private final PatientService patientService;


    @GetMapping("forms/{formId}")
    public Form getFormById(@PathVariable String formId) {
        return formService.getFormById(formId);
    }

    @GetMapping("formAssignments/{formAssignmentId}")
    public FormAssignment getFormAssignmentsById(@PathVariable String formAssignmentId) {
        return formAssignmentService.getFormAssignmentById(formAssignmentId);
    }

    @GetMapping("formAssignments/{formAssignmentId}/formCompletionEntries")
    public List<FormCompletionEntry> getCompletionEntriesByFormAssignmentId(@PathVariable String formAssignmentId) {
        return formAssignmentService.getCompletionEntriesByFormAssignmentId(formAssignmentId);
    }

    @PostMapping("formAssignments/{formAssignmentId}/formCompletionEntries")
    public FormAssignment completeFormAssignment(@RequestBody NewFormCompletionEntryDTO formCompletionEntryDTO, @PathVariable String formAssignmentId) {
        var responses = formCompletionEntryDTO.responses()
                .stream().map( response -> formQuestionResponseService.createFormQuestionResponse(response) )
                .collect(Collectors.toList());
        var formCompletionEntry = formCompletionEntryService.createFormCompletionEntry(responses);
        return formAssignmentService.completeFormAssignment(formAssignmentId, formCompletionEntry);
    }

    @PostMapping(value = "/formAssignments")
    public void assignFormsToPatients(@RequestBody NewFormAssignmentDTO assignmentDTO){
        formService.assignFormsToPatients(assignmentDTO, patientService);
    }
}
