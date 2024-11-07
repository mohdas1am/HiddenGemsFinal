package com.example.HiddenGemsDBMS.Controllers;

import com.example.HiddenGemsDBMS.DTO.MessageToArtisanDTO;
import com.example.HiddenGemsDBMS.DTO.MessageToUserDTO;
import com.example.HiddenGemsDBMS.Models.*;
import com.example.HiddenGemsDBMS.Repository.AcceptRepository;
import com.example.HiddenGemsDBMS.Repository.ArtisanRepository;
import com.example.HiddenGemsDBMS.Repository.ToArtisanMessagesRepository;
import com.example.HiddenGemsDBMS.Repository.ToUserMessagesRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ArtisanController {
    @Autowired
    ArtisanRepository artisanRepository;
    @Autowired
    AcceptRepository acceptRepository;
//    @Autowired
//    ToArtisanMessagesRepository toArtisanMessagesRepository;
//    @Autowired
//    ToUserMessagesRepository toUserMessagesRepository;


    @GetMapping("/artisans/login/{artisan_username}/{password}")
    ResponseEntity<String> loginUser(@PathVariable String artisan_username, @PathVariable String password){
        Optional<Artisans> artisansOptional=artisanRepository.findById(artisan_username);
        if(artisansOptional.isPresent()){
            if(artisansOptional.get().getPassword().equals(password)){
                return ResponseEntity.ok("Logged in as artisan:"+artisansOptional.get().getFname());
            }return ResponseEntity.ok("Incorrect Password");
        }
        return ResponseEntity.badRequest().body("No such artisan found");
    }

//    @PostMapping("/artisans/register")
//    ResponseEntity<String>registerUser(@RequestBody Artisans artisans){
//        Optional<Artisans>artisansOptional=artisanRepository.findById(artisans.getArtisan_username());
//        Optional<Artisans>emailOptional=artisanRepository.findByEmail(artisans.getEmail());
//        if(artisansOptional.isPresent() || emailOptional.isPresent()){
//            return ResponseEntity.badRequest().body("Username or Email already in use");
//        }artisanRepository.save(artisans);
//        return ResponseEntity.ok("Registered as "+artisans.getFname()+" "+artisans.getLname());
//    }

    @PostMapping("/artisans/register")
    ResponseEntity<String>registerUser(@RequestBody Accept artisans){
        Optional<Artisans>artisansOptional=artisanRepository.findById(artisans.getArtisan_username());
        Optional<Artisans>emailOptional=artisanRepository.findByEmail(artisans.getEmail());
        if(artisansOptional.isPresent() || emailOptional.isPresent()){
            return ResponseEntity.badRequest().body("Username or Email already in use");
        }artisans.setStatus(false);
        acceptRepository.save(artisans);
        return ResponseEntity.ok("Will notify you once you are verified ");
    }
}
