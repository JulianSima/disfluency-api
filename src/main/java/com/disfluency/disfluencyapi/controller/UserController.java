package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.users.UserRole;
import com.disfluency.disfluencyapi.dto.users.NewTherapistUserDTO;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
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

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRole login(@RequestBody UserDTO userDTO) {
        log.info(userDTO.toString());
        return userService.getUserRoleByAccount(userDTO);
    }

    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRoleDTO signUp(@RequestBody NewTherapistUserDTO newUser) {
        return userService.createTherapistUser(newUser);
    }
}
