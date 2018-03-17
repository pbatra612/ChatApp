package com.learning.chatapi.ChatApp.model;

import javax.persistence.*;

@Entity
public class Message {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int Id;
    private String message;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    public Message() {
    }

    public Message(String message, Chat chat) {
        this.message = message;
        this.chat = chat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public Chat getChatId() {
//        return chat;
//    }
//
//    public void setChatId(Chat chat) {
//        this.chat = chat;
//    }
}
