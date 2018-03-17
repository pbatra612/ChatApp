package com.learning.chatapi.ChatApp.repo;

import com.learning.chatapi.ChatApp.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Integer> {

    public List<Chat> findAllChatsByFromUserName(String fromUserName);


}

