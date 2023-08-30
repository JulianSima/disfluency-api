package com.disfluency.disfluencyapi.service.analysis;

import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.dto.analysis.AnalysisRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final AnalysisApiClient analysisApiClient;

    public Session getAnalysedSession(String sessionId) {
        // get session from session service
        // build analysis request
        var response = analysisApiClient.getAnalysis(new AnalysisRequest("audioUrlFromSession"));
        var session = new Session();
        session.setTranscription(response.getChunks());
        return session;
    }
}
