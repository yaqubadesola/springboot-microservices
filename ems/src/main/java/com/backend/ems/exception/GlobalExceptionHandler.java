package com.backend.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Resource not found exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest
    ){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    //Email Already exists exception
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException exception, WebRequest webRequest
    ){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                //exception.getMessage(),
                "An unexpected error occurred",
                webRequest.getDescription(false),
                "USER_EMAIL_ALREADY_EXIST"
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidation(
            MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.computeIfAbsent(
                        error.getField(),
                        key -> new ArrayList<>()
                ).add(error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    @Override
//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                               HttpHeaders headers,
//                                                               HttpStatusCode status,
//                                                               WebRequest request){
//        Map<String, String> errors = new HashMap<>();
//        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
//
//        errorList.forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String message = error.getDefaultMessage();
//            errors.put(fieldName, message);
//        });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    //Generic exceptions
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleGenericException(
            Exception exception, WebRequest webRequest
    ){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}