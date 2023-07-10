package com.disfluency.disfluencyapi.service.users;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.domain.users.UserRole;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.disfluency.disfluencyapi.exception.UserNotFoundException;
import com.disfluency.disfluencyapi.repository.PatientRepo;
import com.disfluency.disfluencyapi.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PatientRepo patientRepo;

    public UserRole getUserRoleByCredentials(UserDTO userDTO) {
        var user = userRepo.findOneByAccountAndPassword(userDTO.account(), userDTO.password());
        var userRole = user.orElseThrow(UserNotFoundException::new);
        if(userRole.getRole() instanceof Patient){
            ((Patient) userRole.getRole()).setFcmToken(userDTO.fcmToken());
            patientRepo.save((Patient) userRole.getRole());
        }
        return userRole.getRole();
    }

    public UserRoleDTO createUser(String account, String password, UserRole user) {
        return userRepo.save(new User(account, password, user)).getRole().toUserRoleDTO();
    }
}
