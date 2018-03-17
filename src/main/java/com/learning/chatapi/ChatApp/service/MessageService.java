package com.learning.chatapi.ChatApp.service;

import com.learning.chatapi.ChatApp.model.Chat;
import com.learning.chatapi.ChatApp.model.Message;
import com.learning.chatapi.ChatApp.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;


    public Message post(String message, Chat chat) {
        return messageRepo.save(new Message(message, chat));
    }
}
