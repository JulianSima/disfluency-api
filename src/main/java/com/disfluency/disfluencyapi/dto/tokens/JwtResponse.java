package com.disfluency.disfluencyapi.dto.tokens;

import com.disfluency.disfluencyapi.domain.users.UserRole;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtResponse {

    private String accessToken;
    private String refreshToken;
    private UserRole userRoleDTO;
}
