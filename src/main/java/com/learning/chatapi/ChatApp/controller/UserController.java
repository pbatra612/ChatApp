package com.learning.chatapi.ChatApp.controller;

import com.learning.chatapi.ChatApp.httputils.CustomError;
import com.learning.chatapi.ChatApp.model.Session;
import com.learning.chatapi.ChatApp.model.User;
import com.learning.chatapi.ChatApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody User user) {
        if(userService.isUserExists(user.getUserName())) {
            return new ResponseEntity(new CustomError("User already exists with username " + user.getUserName()), HttpStatus.CONFLICT);
        }
        Session created = userService.add(user);
        if(created == null) {
            return new ResponseEntity(new CustomError("request body error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(getSessionMap(created), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody User user) {
        if(user.getUserName() == null || user.getPassword() == null) {
            return new ResponseEntity(new CustomError("request body error"), HttpStatus.BAD_REQUEST);
        }
        Session created = userService.login(user);
        if(created == null) {
            return new ResponseEntity(new CustomError("password does not match"), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(getSessionMap(created), HttpStatus.OK);
    }

    @PostMapping("/logout")
    ResponseEntity<?> logout(@RequestHeader String XSESSIONID, @RequestHeader String XSIGNATURE) {
        return new ResponseEntity(new CustomError("password does not match"), HttpStatus.FORBIDDEN);
    }

    @GetMapping("/me")
    ResponseEntity<?> getMe(@RequestHeader String XSESSIONID, @RequestHeader String XSIGNATURE) {
        User user = userService.get(XSESSIONID, XSIGNATURE);
        if( user == null) {
            return new ResponseEntity(new CustomError("error"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/{username}/chats")
    Object getChatlist(@PathVariable("username") String userName) {
        return userService.getChats(userName);
    }

    @GetMapping("/healthcheck")
    Object healthcheck() {
        Map<String, String> map = new HashMap<>();
        map.put("status", "ok");
        return map;
    }

    Map<String, String> getSessionMap(Session session) {
        Map<String, String> sessionMap = new HashMap<>();
        sessionMap.put("XSESSIONID", session.getId());
        sessionMap.put("XSIGNATURE", session.getSignature());
        return sessionMap;
    }


}
