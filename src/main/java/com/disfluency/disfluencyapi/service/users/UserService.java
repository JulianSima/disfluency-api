package com.disfluency.disfluencyapi.service.users;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.domain.users.UserRole;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.users.NewTherapistUserDTO;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.disfluency.disfluencyapi.exception.ExistingAccountException;
import com.disfluency.disfluencyapi.exception.UserNotFoundException;
import com.disfluency.disfluencyapi.repository.UserRepo;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import com.disfluency.disfluencyapi.service.therapists.TherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final TherapistService therapistService;
    private final PatientService patientService;

    public UserRole getUserRoleByAccount(UserDTO userDTO) {
        var user = userRepo.findOneByAccount(userDTO.account());
        var userRole = user.orElseThrow(UserNotFoundException::new);
        return userRole.getRole();
    }

    public UserRoleDTO createTherapistUser(NewTherapistUserDTO newUser) {
        validateExistingAccount(newUser.account());
        var therapist = therapistService.createTherapist(newUser.user());
        return createUser(newUser.account(), newUser.password(), therapist).toUserRoleDTO();
    }

    public Patient createPatientForTherapist(NewPatientDTO newPatient, String therapistId) {
        var patient = createPatientUser(newPatient.email(), "12345678", newPatient); //TODO mandar mail
        therapistService.addPatientToTherapist(therapistId, patient);
        return patient;
    }

    private Patient createPatientUser(String account, String password, NewPatientDTO newPatient) {
        validateExistingAccount(account);
        var patient = patientService.createPatient(newPatient);
        createUser(account, password, patient);
        return patient;
    }

    private UserRole createUser(String account, String password, UserRole user) {
        return userRepo.save(new User(account, password, user)).getRole();
    }

    private void validateExistingAccount(String account) {
        if(isAnExistingAccount(account)) {
            throw new ExistingAccountException(account);
        }
    }

    private Boolean isAnExistingAccount(String account) {
        return userRepo.findOneByAccount(account).isPresent();
    }
}
