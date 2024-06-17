package dev.Innocent.service.Impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
    private JavaMailSender javaMailSender;

    public void sendVerificationOtpEmail(String email, String otp) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, "utf-8");

        String subject = "Verify OTP";
        String content = "Your verification OTP code is: " + otp;

        try {
            mimeMessageHelper.setText(content);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(subject);
            javaMailSender.send(message);
        } catch (MailException | MessagingException e) {
            throw new MailSendException(e.getMessage());
        }
    }
}
