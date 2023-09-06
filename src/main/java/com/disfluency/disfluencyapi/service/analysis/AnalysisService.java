package com.disfluency.disfluencyapi.service.analysis;

import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.dto.analysis.AnalysisRequest;
import com.disfluency.disfluencyapi.dto.analysis.ResultsRequest;
import com.disfluency.disfluencyapi.dto.analysis.ResultsResponse;
import com.disfluency.disfluencyapi.service.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final AnalysisApiClient analysisApiClient;
    private final SessionService sessionService;

    public Session createAnalysedSession(String audioUrl, String preSignedUrl) {

        var analysis = analysisApiClient.getAnalysis(new AnalysisRequest(preSignedUrl));
        return sessionService.createSession(audioUrl, analysis);
    }

    public ResultsResponse getSessionResults(String sessionId) {
        var session = sessionService.getSessionById(sessionId);
        return analysisApiClient.getResults(new ResultsRequest("sarasa", session.getTranscription()));
    }
}
