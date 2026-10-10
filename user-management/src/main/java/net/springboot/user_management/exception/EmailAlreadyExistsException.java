package net.springboot.user_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException{
    private String email;

    public EmailAlreadyExistsException(String email){
        super(String.format("User already exists with this email: %s", email));
        this.email  = email;
    }
}
