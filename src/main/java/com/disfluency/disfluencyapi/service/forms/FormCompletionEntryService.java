package com.disfluency.disfluencyapi.service.forms;

import com.disfluency.disfluencyapi.repository.FormCompletionEntryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormCompletionEntryService {

    private final FormCompletionEntryRepo formCompletionEntryRepo;


}
