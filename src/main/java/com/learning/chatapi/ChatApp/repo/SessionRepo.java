package com.learning.chatapi.ChatApp.repo;

import com.learning.chatapi.ChatApp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session, String> {


}

