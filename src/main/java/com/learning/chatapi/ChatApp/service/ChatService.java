package com.learning.chatapi.ChatApp.service;

import com.learning.chatapi.ChatApp.model.Chat;
import com.learning.chatapi.ChatApp.model.Message;
import com.learning.chatapi.ChatApp.repo.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    ChatRepo chatRepo;

    @Autowired
    MessageService messageService;

    List<Chat> getChatsForUser(String userName) {
        return chatRepo.findAllChatsByFromUserName(userName);
    }

    public Chat getById(int Id) {
        return chatRepo.findById(Id).get();
    }

    public List<Message> postMessage(int id, String message) {
        Chat chat = chatRepo.findById(id).get();

        List<Message> msgs = chat.getMessages();
        msgs.add(messageService.post(message, chat));
        chat.setMessages(msgs);
        chatRepo.save(chat);
        return getMessages(chat);
    }

    List<Message> getMessages(Chat chat) {
        return chat.getMessages();
    }

}
