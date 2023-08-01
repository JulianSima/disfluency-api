package com.disfluency.disfluencyapi.domain.users;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class User {

    @Id
    private String id;
    private String account;
    private byte[] password;
    private byte[] salt;
    private UserRole role;


    public User(String account, UserPassword userPassword, UserRole role) {
        this.account = account;
        this.password = userPassword.getSaltedPassword();
        this.salt = userPassword.getSalt();
        this.role = role;
    }
}
