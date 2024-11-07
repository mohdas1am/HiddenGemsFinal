package com.example.HiddenGemsDBMS.Repository;

import com.example.HiddenGemsDBMS.Models.Artisans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtisanRepository extends JpaRepository<Artisans,String> {
    Optional<Artisans> findByEmail(String email);
}
