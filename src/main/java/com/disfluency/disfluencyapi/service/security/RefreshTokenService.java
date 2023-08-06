package com.disfluency.disfluencyapi.service.security;

import com.disfluency.disfluencyapi.domain.tokens.RefreshToken;
import com.disfluency.disfluencyapi.exception.TokenRefreshException;
import com.disfluency.disfluencyapi.repository.RefreshTokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refreshTokenDurationDays}")
    private int refreshTokenDurationDays;

    private final RefreshTokenRepo refreshTokenRepo;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepo.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userId) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .expiryDate(Instant.now().plusSeconds(refreshTokenDurationDays* 86400L))
                .token(UUID.randomUUID().toString())
                .build();

        refreshToken = refreshTokenRepo.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.isExpired()) {
            refreshTokenRepo.delete(refreshToken);
            throw new TokenRefreshException(refreshToken.getToken(),
                    "Refresh token was expired.");
        }

        return refreshToken;
    }

    @Transactional
    public int deleteByUserId(String userId) {
        return refreshTokenRepo.deleteByUserId(userId);
    }
}
