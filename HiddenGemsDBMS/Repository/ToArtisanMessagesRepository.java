package com.example.HiddenGemsDBMS.Repository;

import com.example.HiddenGemsDBMS.DTO.MessageToArtisanDTO;
import com.example.HiddenGemsDBMS.Models.ToArtisanMessages;
import com.example.HiddenGemsDBMS.Models.ToUserMessages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToArtisanMessagesRepository extends JpaRepository<ToArtisanMessages,String> {
    //List<ToArtisanMessages> findByArtisan(String artisanUsername);
}
