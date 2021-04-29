package com.codebenders.sunnywalkbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String message, String emailAddress) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("Sunny Walk reminder");
            mimeMessageHelper.setTo(emailAddress);
            mimeMessageHelper.setText(message);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (Exception e) {
            System.out.println("Error --> " + e.toString());
        }
    }
}
