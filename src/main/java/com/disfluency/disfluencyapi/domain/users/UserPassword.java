package com.disfluency.disfluencyapi.domain.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserPassword {
    private byte[] salt;
    private byte[] saltedPassword;
}
