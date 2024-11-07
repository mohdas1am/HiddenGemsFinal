package com.example.HiddenGemsDBMS.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageToUserDTO {
    private String  messageId;
    private String userUsername;     // User's username who received the message
    private String artisanUsername;  // Artisan's username who sent the message
    private String content;
    private Long timestamp;

    // Constructors
    public MessageToUserDTO(String messageId, String userUsername, String artisanUsername, String content, Long timestamp) {
        this.messageId = messageId;
        this.userUsername = userUsername;
        this.artisanUsername = artisanUsername;
        this.content = content;
        this.timestamp = timestamp;
    }
    public MessageToUserDTO(String artisanUsername, String content, Long timestamp) {
        this.artisanUsername = artisanUsername;
        this.content = content;
        this.timestamp = timestamp;
    }
}
