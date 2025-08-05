package com.ridetogether.ridetogether.controller;

import com.ridetogether.ridetogether.model.ChatMessage;
import com.ridetogether.ridetogether.service.ChatMessageService;
import com.ridetogether.ridetogether.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/chat/{rideId}")
    @SendTo("/topic/ride/{rideId}")
    public ChatMessage sendMsg(@DestinationVariable String rideId, ChatMessage message) throws Exception {

        message.setSentDate(LocalDateTime.now());


        boolean isFile = message.getFileName() != null && message.getFileType() != null;

        if (!isFile) {
            // Encrypt ONLY plain-text chat messages
            try {
                String encrypted = AESUtil.encryptText(message.getContent());
                message.setContent(encrypted);
                message.setEncrypted(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                message.setContent("Error encrypting");
            }
            chatMessageService.save(message);
        } else {
            byte[] encryptedBytes = java.util.Base64.getDecoder().decode(message.getContent());

            if (message.getFileType() != null && message.getFileType().startsWith("image/")) {
                message.setContent("[image]");
            } else if (message.getFileType() != null && message.getFileType().startsWith("video/")) {
                message.setContent("[video]");
            } else {
                message.setContent("[file]");
            }
            message.setEncrypted(false);

            message = chatMessageService.saveMessageWithFile(message, encryptedBytes);
        }
        return message;
    }
}
