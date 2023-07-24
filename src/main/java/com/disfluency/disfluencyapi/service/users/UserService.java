package com.disfluency.disfluencyapi.service.users;

import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.domain.users.UserRole;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.disfluency.disfluencyapi.exception.UserNotFoundException;
import com.disfluency.disfluencyapi.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserRole getUserRoleByCredentials(UserDTO userDTO) {
        var user = userRepo.findOneByAccountAndPassword(userDTO.account(), userDTO.password());
        var userRole = user.orElseThrow(() -> new UserNotFoundException(userDTO.account()));
        return userRole.getRole();
    }

    public UserRoleDTO createUser(String account, String password, UserRole user) {
        return userRepo.save(new User(account, password, user)).getRole().toUserRoleDTO();
    }
}
