package com.disfluency.disfluencyapi.domain.users;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    private String id;
    private String account;
    private String password;
    private UserRole role;


    public User(String account, String password, UserRole role) {
        this.account = account;
        this.password = password;
        this.role = role;
    }
}
