package com.disfluency.disfluencyapi.service.session;

import com.amazonaws.HttpMethod;
import com.disfluency.disfluencyapi.domain.analysis.Analysis;
import com.disfluency.disfluencyapi.domain.sessions.Session;
import com.disfluency.disfluencyapi.dto.analysis.AnalysisResponse;
import com.disfluency.disfluencyapi.repository.SessionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.disfluency.disfluencyapi.service.aws.S3Service.*;


@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepo sessionRepo;

    public Session createSession(Analysis analysis) {
        return sessionRepo.save(Session.newSession(analysis));
    }

    public Session getSessionById(String sessionId) {
        return sessionRepo.findById(sessionId).orElseThrow();
    }

}
