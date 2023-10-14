package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import com.disfluency.disfluencyapi.domain.tokens.RefreshToken;
import com.disfluency.disfluencyapi.dto.patients.PatientInviteConfirmationDTO;
import com.disfluency.disfluencyapi.dto.tokens.JwtResponse;
import com.disfluency.disfluencyapi.dto.tokens.RefreshTokenRequest;
import com.disfluency.disfluencyapi.dto.users.NewTherapistUserDTO;
import com.disfluency.disfluencyapi.dto.users.PatientConfirmationDTO;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.exception.TokenRefreshException;
import com.disfluency.disfluencyapi.exception.UserNotFoundException;
import com.disfluency.disfluencyapi.service.security.JwtService;
import com.disfluency.disfluencyapi.service.security.RefreshTokenService;
import com.disfluency.disfluencyapi.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse signUp(@RequestBody NewTherapistUserDTO newUser) {
        var createdTherapist = userService.createTherapistUser(newUser);
        //TODO: ver de separar en dos endpoints por ahi? o mas bien que este solo de de alta pero no te logge
        return JwtResponse.builder()
                .accessToken(jwtService.generateJwtToken(newUser.account()))
                .refreshToken(refreshTokenService.createRefreshToken(createdTherapist.getId()).getToken())
                .userRoleDTO(createdTherapist.getRole())
                .build();
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

    @GetMapping("/pending/patient/{patientId}")
    public PatientInviteConfirmationDTO getPendingPatientById(@PathVariable String patientId){
        var patient = (Patient) userService.getPendingPatientById(patientId).getRole();
        return new PatientInviteConfirmationDTO(patient.getName(), patient.getLastName(), patient.getEmail(), patient.getProfilePictureUrl());
    }

    @PostMapping(value = "/pending/patient/{patientId}/confirm", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void     confirmPendingPatientById(@PathVariable String patientId, @RequestBody PatientConfirmationDTO patientConfirmation){
        userService.confirmPendingPatient(patientId, patientConfirmation.password());
    }
}
