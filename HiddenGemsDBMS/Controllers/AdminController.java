package com.example.HiddenGemsDBMS.Controllers;

import com.example.HiddenGemsDBMS.DTO.MessageToUserDTO;
import com.example.HiddenGemsDBMS.DTO.MessageToArtisanDTO;
import com.example.HiddenGemsDBMS.Models.*;
import com.example.HiddenGemsDBMS.Repository.*;
import com.example.HiddenGemsDBMS.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AdminController {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ArtisanRepository artisanRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ToArtisanMessagesRepository toArtisanMessagesRepository;
    @Autowired
    AcceptRepository acceptRepository;
//    @Autowired
//    JavaMailSender emailSender;

    @Autowired
    private EmailService emailService;

    @GetMapping("/admin/users")
    List<Users> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/admin/artisans")
    List<Artisans> getAllArtisans(){
        return artisanRepository.findAll();
    }


    @GetMapping("/admin/messages/artisans")
    public List<MessageToArtisanDTO> getMessagesToArtisans() {
        List<ToArtisanMessages> messages = toArtisanMessagesRepository.findAll();
        return messages.stream()
                .map(message -> new MessageToArtisanDTO(
                        message.getMessage_id(),
                        message.getArtisan().getArtisan_username(),  // Fetch artisan's username
                        message.getUser().getUser_username(),       // Fetch user's username
                        message.getContent(),
                        message.getTimestamp()))
                .collect(Collectors.toList());
    }

//    @GetMapping("/admin/messages/users")
//    public List<MessageToUserDTO> getMessagesToUsers() {
//        List<ToUserMessages> messages = toUserMessagesRepository.findAll();
//        return messages.stream()
//                .map(message -> new MessageToUserDTO(
//                        message.getMessage_id(),
//                        message.getUser().getUser_username(),     // Fetch user's username
//                        message.getArtisan().getArtisan_username(), // Fetch artisan's username
//                        message.getContent(),
//                        message.getTimestamp()))
//                .collect(Collectors.toList());
//    }
    @DeleteMapping("/admin/users/{user_username}")
    ResponseEntity<String>deleteUser(@PathVariable String user_username){
        Optional<Users>userToDelete=userRepository.findById(user_username);
        if(userToDelete.isPresent()){
            userRepository.delete(userToDelete.get());
            return ResponseEntity.ok("User "+userToDelete.get().getFname()+"is successfully deleted");
        }return ResponseEntity.ok("No such User");
    }
    @DeleteMapping("/admin/artisan/{artisan_username}")
    ResponseEntity<String>deleteArtisan(@PathVariable String artisan_username){
        Optional<Artisans>artisanToDelete=artisanRepository.findById(artisan_username);
        if(artisanToDelete.isPresent()){
            artisanRepository.delete(artisanToDelete.get());
            return ResponseEntity.ok("User "+artisanToDelete.get().getFname()+"is successfully deleted");
        }return ResponseEntity.ok("No such Artisan");
    }

    @GetMapping("/admin/login/{admin_username}/{password}")
    ResponseEntity<String>loginAdmin(@PathVariable String admin_username,@PathVariable String password){
        Optional<Admin>adminOptional=adminRepository.findById(admin_username);
        if(adminOptional.isPresent()){
            if(adminOptional.get().getPassword().equals(password)){
                return ResponseEntity.ok("Logged in as admin:"+adminOptional.get().getFname());
            }return ResponseEntity.ok("Incorrect Password");
        }
        return ResponseEntity.badRequest().body("No such admin found");
    }

//    @PostMapping("/admin/accept")
//    ResponseEntity<String>acceptArtisan(Accept accept){
//        List<Accept>accepts=acceptRepository.findByStatus(accept.isStatus());
//        for (Accept accept:accepts
//             ) {
//            accept.setStatus(true);
//            artisanRepository.save(accepts);//should be ab;e to save them in database
//            //send mail to the artisan based on the email telling they are approved
//
//        }return ResponseEntity.ok("Artisan added succesfully");
//    }

//    @PostMapping("/admin/accept")
//    public ResponseEntity<String> acceptArtisan() {
//        try {
//            // Find all pending accepts where status is false
//            List<Accept> pendingAccepts = acceptRepository.findByStatus(false);
//
//            if (pendingAccepts.isEmpty()) {
//                return ResponseEntity.ok("No pending artisans found for approval");
//            }
//
//            for (Accept pendingAccept : pendingAccepts) {
//                // Create new Artisan from Accept data
//                Artisans artisan = new Artisans();
//                artisan.setArtisan_username(pendingAccept.getArtisan_username());
//                artisan.setPassword(pendingAccept.getPassword());
//                artisan.setFname(pendingAccept.getFname());
//                artisan.setLname(pendingAccept.getLname());
//                artisan.setEmail(pendingAccept.getEmail());
//                artisan.setContact_number(pendingAccept.getContact_number());
//                artisan.setWork_type(pendingAccept.getWork_type());
//                artisan.setWork_details(pendingAccept.getWork_details());
//                artisan.setLocation(pendingAccept.getLocation());
//                artisan.setStatus(true);
//
//                // Save artisan to database
//                artisanRepository.save(artisan);
//
//                // Update accept status
//                pendingAccept.setStatus(true);
//                acceptRepository.save(pendingAccept);
//            }
//
//            return ResponseEntity.ok(String.format("Successfully approved %d artisans", pendingAccepts.size()));
//
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body("Error processing artisan acceptance: " + e.getMessage());
//        }
//    }

    @GetMapping("/admin/pending")
    public ResponseEntity<List<Accept>> getPendingArtisans() {
        try {
            List<Accept> pendingAccepts = acceptRepository.findByStatus(false);
            return ResponseEntity.ok(pendingAccepts);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/admin/accept/{id}")
    public ResponseEntity<String> acceptArtisan(@PathVariable String id) {
        try {
            // Find the specific pending accept
            Optional<Accept> pendingAcceptOpt = acceptRepository.findById(id);

            if (pendingAcceptOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("No pending artisan found with ID: " + id);
            }

            Accept pendingAccept = pendingAcceptOpt.get();

            // Check if already approved
//            if (pendingAccept.getStatus()) {
//                return ResponseEntity.badRequest().body("Artisan already approved");
//            }

            // Create new Artisan from Accept data
            Artisans artisan = new Artisans();
            artisan.setArtisan_username(pendingAccept.getArtisan_username());
            artisan.setPassword(pendingAccept.getPassword());
            artisan.setFname(pendingAccept.getFname());
            artisan.setLname(pendingAccept.getLname());
            artisan.setEmail(pendingAccept.getEmail());
            artisan.setContact_number(pendingAccept.getContact_number());
            artisan.setWork_type(pendingAccept.getWork_type());
            artisan.setWork_details(pendingAccept.getWork_details());
            artisan.setLocation(pendingAccept.getLocation());
            artisan.setStatus(true);

            // Save artisan to database
            artisanRepository.save(artisan);

            // Update accept status
            pendingAccept.setStatus(true);
            acceptRepository.save(pendingAccept);
            acceptRepository.deleteById(id);
            emailService.sendApprovalEmail(artisan);
            return ResponseEntity.ok("Successfully approved artisan: " + pendingAccept.getArtisan_username());

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error processing artisan acceptance: " + e.getMessage());
        }
    }

//    private void sendApprovalEmail(Artisans artisan) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(artisan.getEmail());
//        message.setSubject("Hidden Gems - Account Approved!");
//        message.setText(String.format(
//                "Dear %s %s,\n\n" +
//                        "Congratulations! Your artisan account has been approved on Hidden Gems.\n\n" +
//                        "You can now log in to your account using your username: %s\n\n" +
//                        "Welcome to our community!\n\n" +
//                        "Best regards,\n" +
//                        "Hidden Gems Team",
//                artisan.getFname(),
//                artisan.getLname(),
//                artisan.getArtisan_username()
//        ));
//
//        emailSender.send(message);
    }


