package com.example.HiddenGemsDBMS.Repository;

import com.example.HiddenGemsDBMS.Models.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photos, Long> {
    List<Photos> findByArtisanUsername(String artisanUsername);
}
