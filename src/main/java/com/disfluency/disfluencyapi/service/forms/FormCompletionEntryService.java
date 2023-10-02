package com.disfluency.disfluencyapi.service.forms;

import com.disfluency.disfluencyapi.domain.forms.FormCompletionEntry;
import com.disfluency.disfluencyapi.domain.forms.FormQuestionResponse;
import com.disfluency.disfluencyapi.repository.FormCompletionEntryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormCompletionEntryService {

    private final FormCompletionEntryRepo formCompletionEntryRepo;

    public FormCompletionEntry createFormCompletionEntry(List<FormQuestionResponse> formsQuestionResponse) {
        var formCompletionEntry = FormCompletionEntry.newFormCompletionEntry(formsQuestionResponse);
        return formCompletionEntryRepo.save(formCompletionEntry);
    }
}
