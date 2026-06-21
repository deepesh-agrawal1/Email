package com.dev.Email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.List;

@Service

public class EmailService {


    @Autowired
    private JavaMailSender mailSender;
    @Async

    public void sendEmail(List<String> emails, String subject, String body) throws MessagingException {
        for (String email : emails) {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(body);

        FileSystemResource file =
                new FileSystemResource(
                        new File("C:/Users/Sai/Project Interview/Email/resume.pdf"));

        helper.addAttachment("Deepesh_agrawal-Resume.pdf", file);

        mailSender.send(message);
    }}
}
