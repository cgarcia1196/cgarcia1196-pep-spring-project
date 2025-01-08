package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.loginInvalidException;
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
        if(accRepository.findByUsername(account.getUsername()).isPresent()){
            throw new registrationDuplicateNameException(account.getUsername());
        }

        //200 OK
        //username is not blank, the password is at least 4 characters long, and an Account with that username does not already exist
        return accRepository.save(account);
    }

    public Account loginUser(Account account) {
        Optional<Account> optionalAccount = accRepository.findByUsername(account.getUsername());
        if(optionalAccount.isPresent()){
             Account repoAcc = optionalAccount.get();
             if(repoAcc.getPassword().equals(account.getPassword())){
                return repoAcc;
             }
        }
        throw new loginInvalidException();
    }

    
}
