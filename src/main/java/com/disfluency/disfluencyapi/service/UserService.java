package com.disfluency.disfluencyapi.service;

import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.domain.users.UserRole;
import com.disfluency.disfluencyapi.dto.therapists.NewTherapistDTO;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.disfluency.disfluencyapi.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final TherapistService therapistService;

    public UserRoleDTO getUserRoleByCredentials(UserDTO userDTO) {
        var user = userRepo.findOneByAccountAndPassword(userDTO.account(), userDTO.password());
        return user.getRole().toUserRoleDTO();
    }

    public UserRoleDTO createTherapistUser(String account, String password, NewTherapistDTO newTherapist) {
        var therapist = therapistService.createTherapist(newTherapist);
        return userRepo.save(new User(account,password,therapist)).getRole().toUserRoleDTO();
    }
}
