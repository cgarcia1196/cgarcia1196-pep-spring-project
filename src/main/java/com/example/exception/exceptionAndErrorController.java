package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class exceptionAndErrorController {
    @ExceptionHandler(registrationInvalidParamsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidParams(registrationInvalidParamsException e){
        return e.getMessage() + ": Username must not be blank and password must be at least 4 characters.";
    }

    @ExceptionHandler(registrationDuplicateNameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody String handleDuplicateName(registrationDuplicateNameException e){
        return e.getMessage() + " username is already used please choose another username.";
    }

    @ExceptionHandler(loginInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody String handleLoginInvalid(loginInvalidException e){
        return "Cannot login username or password incorrect.";
    }

}
