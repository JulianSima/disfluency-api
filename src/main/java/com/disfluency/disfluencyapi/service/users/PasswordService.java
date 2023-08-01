package com.disfluency.disfluencyapi.service.users;

import com.disfluency.disfluencyapi.domain.users.UserPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

@Service
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
        byte[] passwordCheck = encode(password, salt);
        if(!passwordCheck.equals(saltedPassword)) {
            throw new RuntimeException("Error, la contraseña no es correcta");
        }
    }

    private byte[] encode(String password, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            return factory.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return salt;
    }
}
