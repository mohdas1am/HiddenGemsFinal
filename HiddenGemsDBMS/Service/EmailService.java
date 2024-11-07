package com.example.HiddenGemsDBMS.Service;

import com.example.HiddenGemsDBMS.Models.Artisans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendApprovalEmail(Artisans artisan) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(artisan.getEmail());
        message.setSubject("Hidden Gems - Account Approved!");
        message.setText(String.format(
                "Dear %s %s,\n\n" +
                        "Congratulations! Your artisan account has been approved on Hidden Gems.\n\n" +
                        "You can now log in to your account using your username: %s\n\n" +
                        "Welcome to our community!\n\n" +
                        "Best regards,\n" +
                        "Hidden Gems Team",
                artisan.getFname(),
                artisan.getLname(),
                artisan.getArtisan_username()
        ));

        mailSender.send(message);
    }
}
