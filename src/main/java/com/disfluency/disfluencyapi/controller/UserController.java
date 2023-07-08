package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.dto.users.NewTherapistUserDTO;
import com.disfluency.disfluencyapi.dto.users.NotificationDTO;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.disfluency.disfluencyapi.service.notifications.NotificationService;
import com.disfluency.disfluencyapi.service.therapists.TherapistService;
import com.disfluency.disfluencyapi.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TherapistService therapistService;
    private final NotificationService notificationService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRoleDTO login(@RequestBody UserDTO userDTO) {
        return userService.getUserRoleByCredentials(userDTO);
    }

    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRoleDTO signUp(@RequestBody NewTherapistUserDTO newUser) {
        var therapist = therapistService.createTherapist(newUser.user());
        return userService.createUser(newUser.account(), newUser.password(), therapist);
    }

    @PostMapping(value = "/notification", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void notification(@RequestBody NotificationDTO notificationDTO) {
        try {
            notificationService.sendCommonMessage(notificationDTO.title(), notificationDTO.body());
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar la notificacion: " + e.getMessage());
        }
    }
}
