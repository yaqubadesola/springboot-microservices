package com.service.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.BAD_REQUEST)
public class DepartmentCodeAlreadyExistsException extends RuntimeException{
    private String code;

    public DepartmentCodeAlreadyExistsException(String code){
        super(String.format("Department already exists with this code: %s", code));
        this.code  = code;
    }
}
