package com.example.HiddenGemsDBMS.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageToArtisanDTO {
    private String messageId;
    private String artisanUsername;  // Artisan's username (string)
    private String userUsername;     // User's username who sent the message
    private String content;
    private Long timestamp;

    // Constructors
    public MessageToArtisanDTO(String  messageId, String artisanUsername, String userUsername, String content, Long timestamp) {
        this.messageId = messageId;
        this.artisanUsername = artisanUsername;
        this.userUsername = userUsername;
        this.content = content;
        this.timestamp = timestamp;
    }
    public MessageToArtisanDTO(String userUsername, String content, Long timestamp) {
        this.userUsername = userUsername;
        this.content = content;
        this.timestamp = timestamp;
    }

}
