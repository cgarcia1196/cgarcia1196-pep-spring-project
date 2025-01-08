package com.example.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{
    public Optional<Account> findByUsername(String username);
}
