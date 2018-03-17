package com.learning.chatapi.ChatApp.controller;

import com.learning.chatapi.ChatApp.model.Chat;
import com.learning.chatapi.ChatApp.model.Message;
import com.learning.chatapi.ChatApp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping("/chats/{id}")
    Chat getById(@PathVariable("id") int id) {
        return chatService.getById(id);
    }

    @PostMapping("/chats/{id}")
    List<Message> postMessage(@PathVariable("id") int id, @RequestBody Message message) {
        return chatService.postMessage(id, message.getMessage());
    }




}
