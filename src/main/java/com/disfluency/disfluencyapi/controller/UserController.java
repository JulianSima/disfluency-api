package com.disfluency.disfluencyapi.controller;

import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.domain.users.UserRole;
import com.disfluency.disfluencyapi.dto.users.NewTherapistUserDTO;
import com.disfluency.disfluencyapi.dto.users.NotificationDTO;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.disfluency.disfluencyapi.repository.UserRepo;
import com.disfluency.disfluencyapi.service.notifications.NotificationService;
import com.disfluency.disfluencyapi.service.therapists.TherapistService;
import com.disfluency.disfluencyapi.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final TherapistService therapistService;
    private final NotificationService notificationService;

    private final UserRepo userRepo;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRole login(@RequestBody UserDTO userDTO) {
        log.info(userDTO.toString());
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
            notificationService.sendCommonMessage(notificationDTO.title(), notificationDTO.body(), notificationDTO.fcmToken());
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar la notificacion: " + e.getMessage());
        }
    }
}
