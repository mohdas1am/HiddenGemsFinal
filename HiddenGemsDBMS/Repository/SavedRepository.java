package com.example.HiddenGemsDBMS.Repository;

import com.example.HiddenGemsDBMS.Models.Saved;
import com.example.HiddenGemsDBMS.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedRepository extends JpaRepository<Saved,String> {
    List<Saved> findAllByUserUsername(String userUsername);
}
