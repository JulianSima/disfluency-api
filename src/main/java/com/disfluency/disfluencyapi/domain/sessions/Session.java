package com.disfluency.disfluencyapi.domain.sessions;

import com.disfluency.disfluencyapi.domain.analysis.Analysis;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Builder
@Data
public class Session {
    @Id
    private String id;
    private Analysis analysis;

    public static Session newSession(Analysis analysis) {
        return Session
                .builder()
                .analysis(analysis)
                .build();
    }
}
