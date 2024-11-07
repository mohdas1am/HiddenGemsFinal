package com.example.HiddenGemsDBMS.Controllers;

import com.example.HiddenGemsDBMS.DTO.MessageToArtisanDTO;
import com.example.HiddenGemsDBMS.DTO.MessageToUserDTO;
import com.example.HiddenGemsDBMS.Models.Artisans;
import com.example.HiddenGemsDBMS.Models.ToArtisanMessages;
import com.example.HiddenGemsDBMS.Models.ToUserMessages;
import com.example.HiddenGemsDBMS.Models.Users;
import com.example.HiddenGemsDBMS.Repository.ArtisanRepository;
import com.example.HiddenGemsDBMS.Repository.ToArtisanMessagesRepository;
import com.example.HiddenGemsDBMS.Repository.ToUserMessagesRepository;
import com.example.HiddenGemsDBMS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessagesController {
    @Autowired
    ToArtisanMessagesRepository toArtisanMessagesRepository;
    @Autowired
    ToUserMessagesRepository toUserMessagesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ArtisanRepository artisanRepository;

//    @GetMapping("/artisans/{artisan_username}/messages")
//    List<MessageToArtisanDTO>messageToArtisanDTOList(@PathVariable String artisan_username){
//        List<ToArtisanMessages>toArtisanMessages=toArtisanMessagesRepository.findAll();
//        for (ToArtisanMessages x:toArtisanMessages) {
//            if(x.getArtisan().getArtisan_username().equals(artisan_username)){
//                return toArtisanMessages.stream()
//                        .map(toArtisanMessage -> new MessageToArtisanDTO(
//                                toArtisanMessage.getUser().getUser_username(),    // Fetch user's username// Fetch artisan's username
//                                toArtisanMessage.getContent(),
//                                toArtisanMessage.getTimestamp()))
//                        .collect(Collectors.toList());
//            }
//        }return null;
//    }
//    @GetMapping("/users/{user_username}/messages")
//    List<MessageToUserDTO>messageToUserDTOList(@PathVariable String user_username){
//        List<ToUserMessages>toUserMessages=toUserMessagesRepository.findAll();
//        for (ToUserMessages x:toUserMessages) {
//            if(x.getArtisan().getArtisan_username().equals(user_username)){
//                return toUserMessages.stream()
//                        .map(toUserMessage -> new MessageToUserDTO(
//                                toUserMessage.getArtisan().getArtisan_username(),    // Fetch user's username// Fetch artisan's username
//                                toUserMessage.getContent(),
//                                toUserMessage.getTimestamp()))
//                        .collect(Collectors.toList());
//            }
//        }return null;
//    }

//    @PostMapping("/users/{user_username}/{artisan_username}/messages")
//    ResponseEntity<String>sendMessageToArtisan(ToArtisanMessages toArtisanMessages,String message,@PathVariable String user_username,@PathVariable String artisan_username){
//        toArtisanMessages.setTimestamp(System.currentTimeMillis());
//        toArtisanMessages.setContent(message);
//        toArtisanMessages.setUser(userRepository.findById(user_username));
//        return ResponseEntity.ok("Message sent");
//    }
}
