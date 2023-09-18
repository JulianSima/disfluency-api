package com.disfluency.disfluencyapi.service.analysis;

import com.disfluency.disfluencyapi.dto.analysis.AnalysisRequest;
import com.disfluency.disfluencyapi.dto.analysis.AnalysisResponse;
import com.disfluency.disfluencyapi.dto.analysis.ResultsRequest;
import com.disfluency.disfluencyapi.dto.analysis.ResultsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "AnalysisApi", url = "${analysisApi.url}")
public interface AnalysisApiClient {

    @PostMapping(value = "parser", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    AnalysisResponse getAnalysis(AnalysisRequest request);

    @PostMapping(value = "results", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResultsResponse getResults(ResultsRequest request);
}
