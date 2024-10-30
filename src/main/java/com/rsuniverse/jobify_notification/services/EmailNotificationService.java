package com.rsuniverse.jobify_notification.services;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationService {

    private final JavaMailSender mailSender;

    public boolean sendNotification(String to, String from, String email, String subject, String text, boolean isHtml) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(new InternetAddress("notifications@jobify.com", from));
            helper.setTo(new InternetAddress(email, to ));
            helper.setSubject(subject);
            helper.setText(text, isHtml);

            mailSender.send(mimeMessage);
            return true;
        } 
        catch (MessagingException | UnsupportedEncodingException | MailException e) {
            log.error("failed to send email ", e);
            return false;
        }
    }
}
