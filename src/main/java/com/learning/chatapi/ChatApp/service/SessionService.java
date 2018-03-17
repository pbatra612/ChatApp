package com.learning.chatapi.ChatApp.service;

import com.learning.chatapi.ChatApp.model.Session;
import com.learning.chatapi.ChatApp.repo.SessionRepo;
import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Date;

@Service
public class SessionService {

    @Autowired
    SecurityService securityService;

    @Autowired
    SessionRepo sessionRepo;

    Session create(String userName){
        Long signedDate = System.currentTimeMillis() % 1000;
        return sessionRepo.save(new Session(securityService.hash(signedDate.toString() + userName),
                signedDate, userName, null));
    }

    Session validate(String sessionId, String signature) {
        Session session = sessionRepo.findById(sessionId).get();
        if(session != null) {
            System.out.println("Session from db: " + session);
            String createSign = securityService.hash(session.getSignedDate().toString() + session.getUserName());
            if(createSign.equals(signature))
                return session;
        }
        return null;
    }


}
