package com.disfluency.disfluencyapi.dto.tokens;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RefreshTokenResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
}
