package com.example.HiddenGemsDBMS.Service;

import com.example.HiddenGemsDBMS.Models.Saved;
import com.example.HiddenGemsDBMS.Repository.SavedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedService {

    @Autowired
    private SavedRepository savedRepository;

    public Saved saveImage(String userUsername, String path, String artisanUsername) {
        Saved saved = new Saved();
        saved.setUserUsername(userUsername);
        saved.setPath(path);
        saved.setArtisanUsername(artisanUsername);
        return savedRepository.save(saved);
    }

    public List<Saved> getAllSavedByUser(String userUsername) {
        return savedRepository.findAllByUserUsername(userUsername);
    }
}
