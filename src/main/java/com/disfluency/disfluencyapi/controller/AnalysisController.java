package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.dto.analysis.ResultsDTO;
import com.disfluency.disfluencyapi.service.analysis.AnalysisService;
import com.disfluency.disfluencyapi.service.session.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("analysis/{analysisId}/result")
    public ResultsDTO getAnalysisResult(@PathVariable String analysisId) {
        var results = analysisService.getAnalysisResults(analysisId);
        return ResultsDTO.from(results);
    }

}