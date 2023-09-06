package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.dto.analysis.ResultsDTO;
import com.disfluency.disfluencyapi.dto.analysis.ResultsResponse;
import com.disfluency.disfluencyapi.service.analysis.AnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SessionController {

    private final AnalysisService analysisService;

    @GetMapping("session/{sessionId}/result")
    public ResultsDTO getSessionResult(@PathVariable String sessionId) {
        var results = analysisService.getSessionResults(sessionId);
        return ResultsDTO.from(results);
    }

}
