package com.example.HiddenGemsDBMS.Repository;

import com.example.HiddenGemsDBMS.Models.Accept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AcceptRepository extends JpaRepository<Accept,String> {

    List<Accept> findByStatus(boolean status);
}
