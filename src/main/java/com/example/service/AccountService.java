package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.registrationDuplicateNameException;
import com.example.exception.registrationInvalidParamsException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accRepository;

    public AccountService(AccountRepository accRepository){
        this.accRepository = accRepository;
    }

    public Account registerUser(Account account){
        //throws error 
        //code 400 client error
        //ex username blank or password too short
        String badParam = "";
        if(account.getUsername().isBlank() || account.getPassword().length() < 4){
            if(account.getUsername().isBlank()){
                badParam = "username cannot be blank";
            }
            if(account.getPassword().length() < 4){
                badParam+=" password length too short";
            }
            throw new registrationInvalidParamsException(badParam);
        }

        //throws error 
        //code 409 conflict error
        //username already exists
        //
        //if(account.getUsername().equals()) @ToDo connect to database and check if it contains the username
        if(accRepository.findByUsername(account.getUsername()).isPresent()){
            throw new registrationDuplicateNameException(account.getUsername());
        }

        //200 OK
        //username is not blank, the password is at least 4 characters long, and an Account with that username does not already exist
        //
        //return new Account(account.getUsername(), account.getPassword()); @ToDO connect to database and perisist new account to database
        return accRepository.save(account);
    }
}
