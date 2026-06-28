package com.dev.Email.service;

import com.dev.Email.entity.Recipient;
import com.dev.Email.repository.RecipientRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RecipientRepository recipientRepository;

    public void sendEmail(List<String> emails, String subject, String body) throws MessagingException {
        logger.info("Starting email send process for {} recipients with subject: {}", emails.size(), subject);

        for (String email : emails) {

            if (recipientRepository.existsByEmail(email)) {

                logger.info("Skipping {} because it already exists.", email);

                continue;
            }

            try  {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(message, true);
        helper.setFrom("agrawaldev0504@gmail.com");
        helper.setTo(email);
        helper.setSubject(subject);

        // Personalize the body using the recipient's email address.
        // Replace placeholder {name} in the provided body with a friendly name derived from the email local-part.
        String name = extractNameFromEmail(email);
        String personalizedBody = body != null ? body.replace("{name}", name) : body;
        helper.setText(personalizedBody);

        FileSystemResource file =
                new FileSystemResource(
                        new File("C:/Users/Sai/Project Interview/Email/resume.pdf"));

        helper.addAttachment("Deepesh_agrawal-Resume.pdf", file);

                mailSender.send(message);
                Recipient recipient = new Recipient();

                recipient.setEmail(email);

                recipient.setName(extractNameFromEmail(email));

                recipientRepository.save(recipient);
                recipient.setSentAt(LocalDateTime.now());
                logger.info("Email successfully sent to: {} | Subject: {} | Recipient Name: {}", email, subject, extractNameFromEmail(email));
            } catch (Exception e) {
                logger.error("Failed to send email to: {} | Subject: {} | Error: {}", email, subject, e.getMessage(), e);
            }
        }
        logger.info("Email send process completed for subject: {}", subject);
    }

    // Extract a friendly name from the email local-part, e.g. "john.doe@example.com" -> "John Doe"
    private String extractNameFromEmail(String email) {
        if (email == null || !email.contains("@")) return "Friend";
        String local = email.substring(0, email.indexOf('@'));
        String[] parts = local.split("[._-]+");
        StringBuilder name = new StringBuilder();
        for (String p : parts) {
            if (p == null || p.isBlank()) continue;
            name.append(Character.toUpperCase(p.charAt(0)));
            if (p.length() > 1) name.append(p.substring(1));
            name.append(' ');
        }
        String result = name.toString().trim();
        return result.isEmpty() ? "Friend" : result;
    }

}
