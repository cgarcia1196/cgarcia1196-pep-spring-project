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


    public Message findMessageById(int messageId) {
        Optional<Message> optMsg = msgRepo.findById(messageId);//findMessageByMessageId(messageId);
        if(optMsg.isPresent()){
            return optMsg.get();
        }
        return null;
    }


    public Integer deleteMessageById(int messageId) {
        Optional<Message> optMessage = msgRepo.findById(messageId);
        if(optMessage.isPresent()){
            msgRepo.deleteById(messageId);
            return 1;
        }
        return null;
        
    }


    public Integer updateMessageById(Integer messageId, Message newMessage) {
        //check if message is valid

        //check if message exists
        Optional<Message> optMsg = msgRepo.findById(messageId);
        if(optMsg.isEmpty()){
            throw new invalidMessageException("Message does not exist");
        }
        //check if message less than 255 and not blank
        if(newMessage.getMessageText().isBlank() || newMessage.getMessageText().length() > 255){
            throw new invalidMessageException("Message cannot be blank and must be less than 255 characters.");
        }
        //message OK
        Message repoMessage = optMsg.get();
        repoMessage.setMessageText(newMessage.getMessageText());
        msgRepo.save(repoMessage);
        return 1;
    }
}
