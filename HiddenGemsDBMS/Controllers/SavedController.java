package com.example.HiddenGemsDBMS.Controllers;

import com.example.HiddenGemsDBMS.Models.Saved;
import com.example.HiddenGemsDBMS.Service.SavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SavedController {

    @Autowired
    private SavedService savedService;

    @PostMapping("/user/saved/upload")
    public ResponseEntity<Saved> uploadSaved(@RequestParam String userUsername,
                                             @RequestParam String path,
                                             @RequestParam String artisanUsername) {
        Saved saved = savedService.saveImage(userUsername, path, artisanUsername);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user/saved/all")
    public ResponseEntity<List<Saved>> getAllSaved(@RequestParam String userUsername) {
        List<Saved> savedList = savedService.getAllSavedByUser(userUsername);
        return ResponseEntity.ok(savedList);
    }
}