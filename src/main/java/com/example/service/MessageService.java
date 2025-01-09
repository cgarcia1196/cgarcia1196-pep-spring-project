package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.exception.invalidMessageException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private final MessageRepository msgRepo;
    private final AccountRepository accRepo;

    public MessageService(MessageRepository msgRepo, AccountRepository accRepo){
        this.msgRepo = msgRepo;
        this.accRepo = accRepo;
    }
    

    public Message addMessage(Message message) {
        //check that message is valid

        //message cannot be blank and must be less than 255 characters
        String msg = message.getMessageText();
        if(msg.isBlank() || msg.length() > 255){
            throw new invalidMessageException("Message must not be blank and must be less than 255 characters long.");
        }

        //message must be posted by someone with registered account
        Optional<Account> optAccount = accRepo.findById(message.getPostedBy());
        if(optAccount.isEmpty()){
            throw new invalidMessageException("user is not registered");
        }

        //message ok
        return msgRepo.save(message);

    }


    public List<Message> getAllMessages() {
        return (List<Message>)msgRepo.findAll();
    }
}
