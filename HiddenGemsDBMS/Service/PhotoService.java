package com.example.HiddenGemsDBMS.Service;

import com.example.HiddenGemsDBMS.Models.Artisans;
import com.example.HiddenGemsDBMS.Models.Photos;
import com.example.HiddenGemsDBMS.Repository.PhotoRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;
    private String folder="D:/HiddenGems/Photos/";



    public String uploadImage(String title, String description, MultipartFile file,String artisan) throws IOException {
        String filePath=folder+file.getOriginalFilename();
        Photos photos=photoRepository.save(Photos.builder()
                .title(title)
                .description(description)
                .path(filePath)
                .artisanUsername(artisan)
                .build());
        file.transferTo(new File(filePath));
        if (photos!=null){
            return "Uploaded successfully";
        }return null;
    }

    public byte[] getImageById(Long id) throws IOException {
        Optional<Photos>photosOptional=photoRepository.findById(id);
        String filePath=photosOptional.get().getPath();
        byte images[]= Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    @Getter
    public static class ImageResponse {
        private Long id; // Add this field
        private String title;
        private String description;
        private String imageUrl;
        private String artisan;

        public ImageResponse(Long id, String title, String description, String imageUrl, String artisan) {
            this.id = id; // Initialize the new field
            this.title = title;
            this.description = description;
            this.imageUrl = imageUrl;
            this.artisan = artisan;
        }

        // Getters
        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getArtisan() {
            return artisan;
        }
    }

    public List<ImageResponse> getAllImages() {
        List<Photos> allPhotos = photoRepository.findAll();
        List<ImageResponse> imageResponses = new ArrayList<>();

        for (Photos photo : allPhotos) {
            String imageUrl = "/photos/filesystem/" + new File(photo.getPath()).getName();

            // Pass the ID to the ImageResponse constructor
            ImageResponse imageResponse = new ImageResponse(photo.getId(), photo.getTitle(), photo.getDescription(), imageUrl, photo.getArtisanUsername());
            imageResponses.add(imageResponse);
        }

        return imageResponses;
    }

    public List<ImageResponse> getImagesByArtisanUsername(String artisanUsername) throws IOException {
        List<Photos> photosList = photoRepository.findByArtisanUsername(artisanUsername);
        List<ImageResponse> imageResponses = new ArrayList<>();

        for (Photos photo : photosList) {
            ImageResponse imageResponse = new ImageResponse(
                    photo.getId(),
                    photo.getTitle(),
                    photo.getDescription(),
                    photo.getPath(),
                    photo.getArtisanUsername()
            );
            imageResponses.add(imageResponse);
        }

        return imageResponses;
    }

}
