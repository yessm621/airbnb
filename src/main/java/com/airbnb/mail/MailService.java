package com.airbnb.mail;

import com.airbnb.mail.dto.MailSendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender emailSender;

    public void sendEmail(MailSendDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(dto.getEmail());
        message.setSubject(dto.getTitle());
        message.setText(dto.getContent());
        emailSender.send(message);
    }
}
