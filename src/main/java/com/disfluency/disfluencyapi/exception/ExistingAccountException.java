package com.disfluency.disfluencyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExistingAccountException  extends RuntimeException {

    public ExistingAccountException(String username) {
        super("El usuario" + username + " ya existe en el sistema");
    }
}
