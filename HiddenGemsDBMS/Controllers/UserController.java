package com.example.HiddenGemsDBMS.Controllers;

import com.example.HiddenGemsDBMS.Models.Artisans;
import com.example.HiddenGemsDBMS.Models.Users;
import com.example.HiddenGemsDBMS.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ArtisanRepository artisanRepository;
    @Autowired
    ToArtisanMessagesRepository toArtisanMessagesRepository;
    @Autowired
    ToUserMessagesRepository toUserMessagesRepository;

    @GetMapping("/user/login/{user_username}/{password}")
    ResponseEntity<String> loginUser(@PathVariable String user_username, @PathVariable String password){
        Optional<Users> usersOptional=userRepository.findById(user_username);
        if(usersOptional.isPresent()){
            if(usersOptional.get().getPassword().equals(password)){
                return ResponseEntity.ok("Logged in as user:"+usersOptional.get().getFname());
            }return ResponseEntity.ok("Incorrect Password");
        }
        return ResponseEntity.badRequest().body("No such user found");
    }

    @PostMapping("/user/register")
    ResponseEntity<String>registerUser(@RequestBody Users users){
        Optional<Users>usersOptional=userRepository.findById(users.getUser_username());
        Optional<Users>emailOptional=userRepository.findByEmail(users.getEmail());
        if(usersOptional.isPresent() || emailOptional.isPresent()){
            return ResponseEntity.badRequest().body("Username or Email already in use");
        }userRepository.save(users);
        return ResponseEntity.ok("Registered as "+users.getFname()+" "+users.getLname());
    }

    @GetMapping("/users/artisans")
    List<Artisans>getSomeArtisans(){
        return artisanRepository.findAll();
    }

    @GetMapping("/users/artisans/{username}")
    Optional<Artisans> getArtisanById(@PathVariable String username){
        return artisanRepository.findById(username);
    }

}
