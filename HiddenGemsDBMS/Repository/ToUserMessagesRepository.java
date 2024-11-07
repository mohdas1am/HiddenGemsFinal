package com.example.HiddenGemsDBMS.Repository;

import com.example.HiddenGemsDBMS.Models.ToUserMessages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToUserMessagesRepository extends JpaRepository<ToUserMessages,String> {
    //List<ToUserMessages> findByUser(String userUsername);
}
