package com.example.HiddenGemsDBMS.Controllers;

import com.example.HiddenGemsDBMS.DTO.PhotoDetailsResponse;
import com.example.HiddenGemsDBMS.Models.Artisans;
import com.example.HiddenGemsDBMS.Models.Photos;
import com.example.HiddenGemsDBMS.Repository.PhotoRepository;
import com.example.HiddenGemsDBMS.Service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController
public class PhotoController {
    @Autowired
    PhotoService photoService;
    @Autowired
    PhotoRepository photoRepository;


    @PostMapping("/photos/upload")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("image")MultipartFile file,@RequestParam("artisan")String artisan) throws IOException {
        String uploadImage = photoService.uploadImage(title,description,file,artisan);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }


    @GetMapping("/photos/{id}")
    public ResponseEntity<?> getPhotoWithDetails(@PathVariable Long id) throws IOException {
        // Fetch the photo metadata by fileName (which is the ID here)
        Optional<Photos> photosOptional = photoRepository.findById(id);

        if (photosOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Photo not found");
        }

        Photos photo = photosOptional.get();

        // Get image data as byte array from the file system
        byte[] imageData = photoService.getImageById(id);

        // Encode the image data to Base64 to display it inline in the HTML
        String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);

        // Build the HTML response
        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<html><body>");
        htmlResponse.append("<h1>Photo Details</h1>");

        htmlResponse.append("<div style='margin-bottom: 20px;'>")
                .append("<h3>").append(photo.getTitle()).append("</h3>") // Title
                .append("<p>").append(photo.getDescription()).append("</p>") // Description
                .append("<p><strong>Artisan:</strong> ").append(photo.getArtisanUsername()).append("</p>") // Artisan
                .append("<img src='data:image/png;base64,").append(base64Image) // Image encoded to Base64
                .append("' width='300' alt='Image'/>")
                .append("</div>");

        htmlResponse.append("</body></html>");

        // Return the HTML content
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_HTML).body(htmlResponse.toString());
    }

    @GetMapping("/photos/details/{id}")
    public ResponseEntity<PhotoDetailsResponse> getPhotoDetails(@PathVariable Long id) {
        Optional<Photos> optionalPhoto = photoRepository.findById(id);

        if (optionalPhoto.isPresent()) {
            Photos photo = optionalPhoto.get();

            // Assuming you have a PhotoDetailsResponse DTO
            PhotoDetailsResponse response = new PhotoDetailsResponse(
                    photo.getId(),
                    photo.getTitle(),
                    photo.getDescription(),
                    photo.getPath(),
                    photo.getArtisanUsername()
            );

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/photos/all")
    public ResponseEntity<?> getAllPhotos() throws IOException {
        List<PhotoService.ImageResponse> imageResponses = photoService.getAllImages();
        StringBuilder htmlResponse = new StringBuilder();

        htmlResponse.append("<html><body>");
        htmlResponse.append("<h1>Image Gallery</h1>");

        for (PhotoService.ImageResponse imageResponse : imageResponses) {
            // Fetch the image data using the ID
            byte[] imageData = photoService.getImageById(imageResponse.getId());

            // Encode the image data to Base64 for inline display in HTML
            String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);

            // Add the image tag, including a hidden ID attribute
            htmlResponse.append("<div style='margin-bottom: 20px;'>")
                    .append("<img src='data:image/png;base64,").append(base64Image)
                    .append("' width='300' alt='Image' data-id='").append(imageResponse.getId()).append("'/>") // Hidden ID
                    .append("</div>");
        }

        htmlResponse.append("</body></html>");
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_HTML).body(htmlResponse.toString());
    }



    @GetMapping("/photos/byArtisan/{username}")
    public ResponseEntity<?> getPhotosByArtisan(@PathVariable String username) throws IOException {
        List<PhotoService.ImageResponse> imageResponses = photoService.getImagesByArtisanUsername(username);

        if (imageResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No photos found for this artisan");
        }

        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<html><body>");
        htmlResponse.append("<h1>Artisan Gallery: ").append(username).append("</h1>");

        for (PhotoService.ImageResponse imageResponse : imageResponses) {
            byte[] imageData = photoService.getImageById(imageResponse.getId());
            String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);

            htmlResponse.append("<div style='margin-bottom: 20px;'>")
                    .append("<h3>").append(imageResponse.getTitle()).append("</h3>")
                    .append("<p>").append(imageResponse.getDescription()).append("</p>")
                    .append("<img src='data:image/png;base64,").append(base64Image)
                    .append("' width='300' alt='").append(imageResponse.getTitle()).append("' ")
                    .append("data-id='").append(imageResponse.getId()).append("'/>")
                    .append("</div>");
        }

        htmlResponse.append("</body></html>");
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_HTML).body(htmlResponse.toString());
    }

}
