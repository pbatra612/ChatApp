package com.learning.chatapi.ChatApp.service;

import com.learning.chatapi.ChatApp.model.Chat;
import com.learning.chatapi.ChatApp.repo.UserRepo;
import com.learning.chatapi.ChatApp.model.Session;
import com.learning.chatapi.ChatApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordService passwordService;

    @Autowired
    SessionService sessionService;

    @Autowired
    ChatService chatService;


    public Session add(User user) {
        if(user.getPassword() == null || user.getPassword().equalsIgnoreCase("")) {
            return null;
        }
        String saltedHashPwd = passwordService.saltedHashPassword(user.getPassword());
        user.setPassword(saltedHashPwd);
        if(userRepo.save(user) == null) {
            return null;
        }
        return sessionService.create(user.getUserName());
    }

    public Session login(User user) {
        if(passwordService.checkPassword(user.getPassword(), userRepo.findByUserName(user.getUserName()).getPassword())) {
            return sessionService.create(user.getUserName());
        }
        return null;
    }

    public boolean logout() {
        return false;
    }

    public User get(String sessionId, String signature) {
        Session session = sessionService.validate(sessionId, signature);
        if(session == null) {
            return null;
        }
        return userRepo.findByUserName(session.getUserName());
    }

    public List<Chat> getChats(String userName) {
        return chatService.getChatsForUser(userName);
        //return userRepo.findByUserName(userName).getChatList();
    }

    public boolean isUserExists(String userName) {
        if(userRepo.findByUserName(userName) == null) {
            return false;
        }
        return true;
    }

}
