package com.disfluency.disfluencyapi.domain.tokens;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Builder
@Getter
public class RefreshToken {

    @Id
    private String id;
    private String token;
    private Instant expiryDate;
    private String userId;

    public boolean isExpired() {
        return expiryDate.isBefore(Instant.now());
    }
}
