package com.company.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/contact")
    public String showContactForm() {
        return "contact-form";
    }

    @PostMapping("/contact")
    public String submitContactForm(HttpServletRequest request) {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String text = request.getParameter("message");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo("nazar.ua87@gmail.com");

        String mailSubject = fullName + " has sent you a message.";
        String mailContent = "Sender name: " + fullName + "\n" +
                             "Sender email: " + email + "\n" +
                             "Message: " + text + "\n";

        message.setSubject(mailSubject);
        message.setText(mailContent);

        mailSender.send(message);




        return "message";
    }
}