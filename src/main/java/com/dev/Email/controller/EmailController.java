package com.dev.Email.controller;

import com.dev.Email.dto.EmailDto;
import com.dev.Email.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/send")
    public String sendPdf(
            @RequestBody List<String> emails) throws MessagingException {


        emailService.sendEmail(emails,
                "Can I Add Value to Your Engineering Team? | Ex- Intern HSBC",
                "Hi {name},\n\n" +
                        "I hope you are doing well.\n\n" +
                        "My name is Deepesh Agrawal, and I recently completed my B.Tech in Computer Science and Design.\n\n" +
                        "I expect interest from multiple employers; I’d value the chance to speak with your team soon so I can demonstrate the impact I’ll bring."+
                        "I am reaching out to express my interest in Software Engineering opportunities . During my internship, I worked on backend development using Java, Spring Boot, REST APIs, PostgreSQL, Git, Jenkins, and Ansible. I also gained experience working in an enterprise development environment and contributing to production-oriented projects.\n\n" +
                        "I am particularly interested in entry-level Software Engineering and technology-focused roles where I can contribute while continuing to learn and grow.\n\n" +
                        "I have attached my resume for your review. If there are any suitable openings that match my profile, I would be grateful for an opportunity to be considered.\n\n" +
                        "As I expect interest from multiple employers, I would appreciate the opportunity to speak with your team early — I am enthusiastic to demonstrate how I can add value.\n\n" +
                        "Thank you for your time and consideration. I look forward to hearing from you.\n\n" +
                        "Best Regards,\n" +
                        "Deepesh Agrawal\n" +
                        "Phone: 9527034785\n" +
                        "Email:adevagrawal@gmail.com");

        return "PDF Email Sent Successfully";
    }
    @PostMapping("/send1")
    public String sendEmail(
            @RequestBody EmailDto request)
            throws MessagingException {

        // Pass recipients list directly and include {name} placeholder in the body for personalization
        emailService.sendEmail(
                request.getRecipients(),
                request.getSubject(),
                request.getBody());

        return "Emails Sent";
    }
}
