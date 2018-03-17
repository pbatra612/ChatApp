package com.learning.chatapi.ChatApp.repo;

import com.learning.chatapi.ChatApp.model.Chat;
import com.learning.chatapi.ChatApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    public User findByUserName(String userName);

    //public List<Chat> findAllChatsByUserName(String userName);


}
