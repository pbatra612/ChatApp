package com.learning.chatapi.ChatApp.repo;

import com.learning.chatapi.ChatApp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Integer> {


}