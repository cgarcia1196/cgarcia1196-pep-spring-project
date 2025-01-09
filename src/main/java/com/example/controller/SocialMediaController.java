package com.example.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {
    
    private AccountService accServ;
    private MessageService msgServ;
    @Autowired
    public SocialMediaController(AccountService accServ, MessageService msgServ){
        this.accServ = accServ;
        this.msgServ = msgServ;
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Account> registerUser(@RequestBody Account account){
        return new ResponseEntity<>(accServ.registerUser(account), HttpStatus.OK);   
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Account> loginUser(@RequestBody Account account){
        return new ResponseEntity<>(accServ.loginUser(account), HttpStatus.OK);
    }

    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<Message> addMessage(@RequestBody Message message){
        return new ResponseEntity<>(msgServ.addMessage(message), HttpStatus.OK);
    }

    @GetMapping("/messages")
    public @ResponseBody ResponseEntity<List<Message>> getAllMessages(){
        return new ResponseEntity<>(msgServ.getAllMessages(), HttpStatus.OK);
    }

    @GetMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Message> getMessagesById(@PathVariable int messageId){
        return new ResponseEntity<>(msgServ.findMessageById(messageId), HttpStatus.OK);
    }

}
