package com.disfluency.disfluencyapi.domain.sessions;

import com.disfluency.disfluencyapi.domain.analysis.Analysis;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Builder
@Data
public class Session {
    @Id
    private String id;
    @DocumentReference
    private Analysis analysis;

    public static Session newSession(Analysis analysis) {
        return Session
                .builder()
                .analysis(analysis)
                .build();
    }
}
