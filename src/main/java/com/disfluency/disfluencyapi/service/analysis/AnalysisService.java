package com.disfluency.disfluencyapi.service.analysis;

import com.disfluency.disfluencyapi.domain.analysis.Analysis;
import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.dto.analysis.AnalysisRequest;
import com.disfluency.disfluencyapi.dto.analysis.ResultsRequest;
import com.disfluency.disfluencyapi.dto.analysis.ResultsResponse;
import com.disfluency.disfluencyapi.repository.AnalysisRepo;
import com.disfluency.disfluencyapi.service.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final AnalysisApiClient analysisApiClient;
    private final AnalysisRepo analysisRepo;
    private final SessionService sessionService;

    public Analysis createAnalysis(String audioUrl, String preSignedUrl){
        var analysis = analysisApiClient.getAnalysis(new AnalysisRequest(preSignedUrl));
        return analysisRepo.save(Analysis.newAnalysis(audioUrl, analysis));
    }

    public Session createAnalysedSession(String audioUrl, String preSignedUrl) {
        var analysis = createAnalysis(audioUrl, preSignedUrl);
        return sessionService.createSession(analysis);
    }

    public Analysis getAnalysisById(String analysisId) {
        return analysisRepo.findById(analysisId).orElseThrow();
    }

    public ResultsResponse getSessionResults(String sessionId) {
        var session = sessionService.getSessionById(sessionId);
        return getAnalysisResults(session.getAnalysis().getId());
    }

    public ResultsResponse getAnalysisResults(String analysisId) {
        var analysis = getAnalysisById(analysisId);
        return analysisApiClient.getResults(new ResultsRequest("sarasa", analysis.getTranscription()));
    }
}
