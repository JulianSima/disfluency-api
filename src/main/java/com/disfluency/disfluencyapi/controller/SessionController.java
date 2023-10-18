package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.analysis.Analysis;
import com.disfluency.disfluencyapi.dto.analysis.ResultsDTO;
import com.disfluency.disfluencyapi.dto.session.UpdatedSessionDTO;
import com.disfluency.disfluencyapi.service.analysis.AnalysisService;
import com.disfluency.disfluencyapi.service.session.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SessionController {

    private final AnalysisService analysisService;
    private final SessionService sessionService;

    @GetMapping("session/{sessionId}/result")
    public ResultsDTO getSessionResult(@PathVariable String sessionId) {
        var results = analysisService.getSessionResults(sessionId);
        return ResultsDTO.from(results);
    }

    @GetMapping("session/{sessionId}")
    public Analysis getSession(@PathVariable String sessionId) {
        return analysisService.getAnalysisById(sessionId);
    }

    @PutMapping(value = "session/{sessionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Analysis updatedAnalysis(@PathVariable String sessionId, @RequestBody UpdatedSessionDTO updatedSessionDTO) {
        return analysisService.updateAnalysisWithId(sessionId, updatedSessionDTO);
    }

}
