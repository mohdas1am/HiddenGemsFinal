package com.example.HiddenGemsDBMS.DTO;

import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDetailsResponse {
    private Long id;
    private String title;
    private String description;
    private String fileName;
    private String artisanUsername;

    public PhotoDetailsResponse(Long id, String title, String description, String fileName, String artisanUsername) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.fileName = fileName;
        this.artisanUsername = artisanUsername;
    }

    // Getters and Setters
}
