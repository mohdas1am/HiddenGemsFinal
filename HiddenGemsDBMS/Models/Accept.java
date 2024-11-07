package com.example.HiddenGemsDBMS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "accept")
public class Accept {
    @Id
    private String artisan_username;
    private String password;
    private String fname;
    private String lname;
    private String email;
    private String contact_number;
    private String work_type;
    private String work_details;
    private String location;
    private boolean status;
}
