package com.disfluency.disfluencyapi.service;

import com.disfluency.disfluencyapi.domain.therapist.Therapist;
import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.domain.users.UserRole;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserRole getUserRoleByCredentials(UserDTO userDTO) {
        var user = userRepo.findOneByAccountAndPassword(userDTO.account(), userDTO.password());
        return user.getRole();
    }
}
