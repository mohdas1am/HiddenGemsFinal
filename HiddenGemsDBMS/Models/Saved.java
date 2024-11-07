package com.example.HiddenGemsDBMS.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@Table(name = "saved")
public class Saved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long savedId;
    private String userUsername;
    private String path;
    private String artisanUsername;
}
