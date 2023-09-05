package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.service.forms.FormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FormController {

    private final FormService formService;

    @GetMapping("forms/{formId}")
    public Form getFormById(@PathVariable String formId) {
        return formService.getFormById(formId).orElseThrow();
    }
}
