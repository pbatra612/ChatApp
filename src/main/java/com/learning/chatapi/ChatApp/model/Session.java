package com.learning.chatapi.ChatApp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Session {

    @Id
    @GenericGenerator(name="idGenerator", strategy="com.learning.chatapi.ChatApp.service.IDGenerator")
    @GeneratedValue(generator="idGenerator")
    private String Id;
    private String signature;
    private Long signedDate;
    private String userName;
    private Long expiryDate;

    public Session() {
    }

    public Session(String signature, Long signedDate, String userName, Long expiryDate) {
        this.signature = signature;
        this.signedDate = signedDate;
        this.userName = userName;
        this.expiryDate = expiryDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Long signedDate) {
        this.signedDate = signedDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }
}
