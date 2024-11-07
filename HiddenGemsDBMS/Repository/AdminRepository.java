package com.example.HiddenGemsDBMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.HiddenGemsDBMS.Models.Admin;
public interface AdminRepository extends JpaRepository<Admin,String> {
}
