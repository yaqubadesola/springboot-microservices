package com.service.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyExistsException extends RuntimeException{
    private String email;

    public EmployeeAlreadyExistsException(String email){
        super(String.format("Employee already exists with this email: %s", email));
        this.email  = email;
    }
}
