package com.ridetogether.ridetogether.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="chat_messages")
@Data
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String sender;//senderId
    private String senderName;

    @Column(nullable = true)
    private String content;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sentDate;
    @Column(nullable = false)
    private String rideId;

    private String fileName;
    private String fileType;
    private boolean encrypted;
    private String filePath;


}
