package com.disfluency.disfluencyapi.service.users;

import com.disfluency.disfluencyapi.domain.users.UserPassword;
import com.disfluency.disfluencyapi.exception.InvalidPasswordException;
import com.disfluency.disfluencyapi.exception.SaltException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

@Service
@Slf4j
@RequiredArgsConstructor
public class PasswordService {

    private final String algorithm = "PBKDF2WithHmacSHA1";
    private final int ITERATIONS = 10000;
    private final int KEY_LENGTH = 256;

    public UserPassword createPasswordHash(String password) {
        byte[] salt =  generateSalt();
        byte[] saltedPassword = encode(password, salt);
        return new UserPassword(salt, saltedPassword);
    }

    public void validatePassword(String password, byte[] saltedPassword, byte[] salt) {
        String passwordToCheck = new String(encode(password, salt));
        String realPassword = new String (saltedPassword);
        if(!passwordToCheck.equals(realPassword)) {
            throw new InvalidPasswordException();
        }
    }

    private byte[] encode(String password, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            return factory.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new SaltException();
        }
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return salt;
    }
}
