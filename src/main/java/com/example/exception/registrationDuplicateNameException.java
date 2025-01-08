package com.example.exception;

public class registrationDuplicateNameException extends RuntimeException{
    public registrationDuplicateNameException(String message){
        super(message);
    }
}
