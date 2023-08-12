package com.disfluency.disfluencyapi.service.users.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender sender;

    public void sendEmail(String message, String email, String subject) {
        var mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo(email);
            helper.setText(message, true);
            helper.setSubject(subject);
            sender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
