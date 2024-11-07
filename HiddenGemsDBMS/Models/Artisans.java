package com.example.HiddenGemsDBMS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Table(name = "artisans")
public class Artisans {
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


    @Override
    public String toString() {
        return "Artisans{" +
                "artisan_username='" + artisan_username + '\'' +
                ", password='" + password + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", work_type='" + work_type + '\'' +
                ", work_details='" + work_details + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }

    public String getArtisan_username() {
        return artisan_username;
    }

    public void setArtisan_username(String artisan_username) {
        this.artisan_username = artisan_username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getWork_type() {
        return work_type;
    }

    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }

    public String getWork_details() {
        return work_details;
    }

    public void setWork_details(String work_details) {
        this.work_details = work_details;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

}
