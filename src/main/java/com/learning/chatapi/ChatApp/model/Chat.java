package com.learning.chatapi.ChatApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Chat {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int Id;

    //@ManyToOne
    //@JoinColumn(name = "chat_user", nullable = false)
    private String fromUserName;
    private String toUserName;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    public Chat() {
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
