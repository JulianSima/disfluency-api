package com.disfluency.disfluencyapi.service.users;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import com.disfluency.disfluencyapi.domain.state.PatientUserState;
import com.disfluency.disfluencyapi.domain.therapists.Therapist;
import com.disfluency.disfluencyapi.domain.users.User;
import com.disfluency.disfluencyapi.domain.users.UserPassword;
import com.disfluency.disfluencyapi.domain.users.UserRole;
import com.disfluency.disfluencyapi.dto.patients.NewPatientDTO;
import com.disfluency.disfluencyapi.dto.users.NewTherapistUserDTO;
import com.disfluency.disfluencyapi.dto.users.PatientConfirmationDTO;
import com.disfluency.disfluencyapi.dto.users.UserDTO;
import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.disfluency.disfluencyapi.exception.ExistingAccountException;
import com.disfluency.disfluencyapi.exception.UserNotFoundException;
import com.disfluency.disfluencyapi.repository.UserRepo;
import com.disfluency.disfluencyapi.service.patients.PatientService;
import com.disfluency.disfluencyapi.service.therapists.TherapistService;
import com.disfluency.disfluencyapi.service.users.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final TherapistService therapistService;
    private final PatientService patientService;
    private final PasswordService passwordService;
    private final EmailService emailService;

    public User getUserByAccount(UserDTO userDTO) {
        var user = userRepo.findOneByAccount(userDTO.account())
                        .orElseThrow( () -> new UserNotFoundException(userDTO.account()) );
        passwordService.validatePassword(userDTO.password(), user.getPassword(), user.getSalt());
        if(user.getRole() instanceof Patient){
            patientService.setFcmToken((Patient) user.getRole(), userDTO.fcmToken());
        } else {
            therapistService.setFcmToken((Therapist) user.getRole(), userDTO.fcmToken());
        }
        return user;
    }

    public User getUserById(String id){
        return userRepo.findById(id).orElseThrow( () -> new UserNotFoundException(id) );
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findOneByAccount(username);
    }

    public User createTherapistUser(NewTherapistUserDTO newUser) {
        validateExistingAccount(newUser.account());
        var therapist = therapistService.createTherapist(newUser.user());
        return createUser(newUser.account(), newUser.password(), therapist);
    }

    public Patient createPatientForTherapist(NewPatientDTO newPatient, String therapistId) {
        Patient patient = (Patient) createPatientUser(newPatient).getRole();
        therapistService.addPatientToTherapist(therapistId, patient);
        return patient;
    }

    private User createPatientUser(NewPatientDTO newPatient) {
        validateExistingAccount(newPatient.email());

        var patient = patientService.createPatient(newPatient);
        var user = new User(newPatient.email(), patient);
        var persistedUser = userRepo.save(user);

        emailService.sendPatientInvitationEmail(persistedUser.getId(), newPatient.email());
        return persistedUser;
    }

    private User createUser(String account, String password, UserRole user) {
        UserPassword userPassword = passwordService.createPasswordHash(password);
        return userRepo.save(new User(account, userPassword, user));
    }

    private void validateExistingAccount(String account) {
        if(isAnExistingAccount(account)) {
            throw new ExistingAccountException(account);
        }
    }

    private Boolean isAnExistingAccount(String account) {
        return userRepo.findOneByAccount(account).isPresent();
    }

    public User getPendingPatientById(String id) {
        var user = userRepo.findById(id).orElseThrow( () -> new UserNotFoundException(id) );

        if (user.getRole() instanceof Therapist)
            throw new UserNotFoundException(id);

        var patient = (Patient) user.getRole();
        if (patient.getState() != PatientUserState.PENDING)
            throw new ExistingAccountException(user.getAccount());

        return user;
    }

    public void confirmPendingPatient(String patientId, String password){
        User targetUser = getPendingPatientById(patientId);
        UserPassword userPassword = passwordService.createPasswordHash(password);
        targetUser.setPassword(userPassword);
        userRepo.save(targetUser);

        var patient = (Patient) targetUser.getRole();
        patientService.confirmPatient(patient);
    }
}
