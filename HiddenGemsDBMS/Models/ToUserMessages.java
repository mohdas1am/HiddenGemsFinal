package com.example.HiddenGemsDBMS.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@Table(name = "messagetouser")
public class ToUserMessages {
    @Id
    @GeneratedValue()
    private String message_id;
    @ManyToOne
    @JoinColumn(name="user_username",referencedColumnName = "user_username" )
    private Users user;
    @ManyToOne
    @JoinColumn(name="artisan_username",referencedColumnName = "artisan_username" )
    private Artisans artisan;
    private String content;
    private Long timestamp;

    public ToUserMessages() {
    }

    @Override
    public String toString() {
        return "ToUserMessages{" +
                "message_id='" + message_id + '\'' +
                ", user=" + user +
                ", artisan=" + artisan +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
