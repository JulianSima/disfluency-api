package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.tokens.RefreshToken;
import com.disfluency.disfluencyapi.dto.tokens.JwtResponse;
import com.disfluency.disfluencyapi.dto.tokens.RefreshTokenRequest;
import com.disfluency.disfluencyapi.dto.users.NewTherapistUserDTO;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.disfluency.disfluencyapi.exception.TokenRefreshException;
import com.disfluency.disfluencyapi.service.security.JwtService;
import com.disfluency.disfluencyapi.service.security.RefreshTokenService;
import com.disfluency.disfluencyapi.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse login(@RequestBody UserDTO userDTO) {
        var user = userService.getUserByAccount(userDTO);
        var username = userDTO.account();
        return JwtResponse.builder()
                .accessToken(jwtService.generateJwtToken(username))
                .refreshToken(refreshTokenService.createRefreshToken(user.getId()).getToken())
                .userRoleDTO(user.getRole())
                .build();
    }

    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRoleDTO signUp(@RequestBody NewTherapistUserDTO newUser) {
        return userService.createTherapistUser(newUser);
    }

    @PostMapping(value = "/refreshToken", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshRequest) {
        var refreshToken = refreshRequest.refreshToken();
        return refreshTokenService
                .findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserId)
                .map(userId -> {
                    var user = userService.getUserById(userId);
                    var newAccessToken = jwtService.generateJwtToken(user.getAccount());

                    return JwtResponse.builder()
                            .refreshToken(refreshToken)
                            .accessToken(newAccessToken)
                            .userRoleDTO(user.getRole())
                            .build();
                    }
                ).orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
    }
}
