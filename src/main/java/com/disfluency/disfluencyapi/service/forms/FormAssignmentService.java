package com.disfluency.disfluencyapi.service.forms;

import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import com.disfluency.disfluencyapi.domain.forms.FormCompletionEntry;
import com.disfluency.disfluencyapi.repository.FormAssignmentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormAssignmentService {

    private final FormAssignmentRepo formAssignmentRepo;

    public FormAssignment getFormAssignmentById(String formAssignmentId) {
        return formAssignmentRepo.findById(formAssignmentId).orElseThrow();
    }

    public List<FormCompletionEntry> getCompletionEntriesByFormAssignmentId(String formAssignmentId) {
        var formAssignment = getFormAssignmentById(formAssignmentId);
        return formAssignment.getCompletionEntries();
    }

    public List<FormCompletionEntry> completeFormAssignment(String formAssignmentId) {
        var formAssignment = getFormAssignmentById(formAssignmentId);
        formAssignment.addCompletion
    }
}
