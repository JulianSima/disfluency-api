package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.service.analysis.AnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("analysis/test")
    public Session getAnalysisTest() {
        return analysisService.getAnalysedSession("test");
    }
}
