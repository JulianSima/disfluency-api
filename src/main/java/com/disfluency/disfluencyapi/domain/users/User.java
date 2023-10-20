package com.disfluency.disfluencyapi.domain.users;

import com.disfluency.disfluencyapi.domain.patients.Patient;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@RequiredArgsConstructor
public class User {

    @Id
    private String id;
    private String account;
    private byte[] password;
    private byte[] salt;

    @DBRef
    private UserRole role;


    public User(String account, UserPassword userPassword, UserRole role) {
        this.account = account;
        setPassword(userPassword);
        this.role = role;
    }

    public User(String account, Patient patient){
        this.account = account;
        this.role = patient;
    }

    public void setPassword(UserPassword userPassword){
        if (this.password != null || this.salt != null){
            throw new IllegalStateException("Tried to change password and salt of user: " + id);
        }

        this.password = userPassword.getSaltedPassword();
        this.salt = userPassword.getSalt();
    }
}
