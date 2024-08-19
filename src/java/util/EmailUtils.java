/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class EmailUtils {
    private static final String GMAIL_SENT = "truongtqhe172652@fpt.edu.vn";
    private static final String GMAIL_PASSWORD = "mdpb oftj hikf gpvx";
    

    public static boolean sendMail(String emailTo, String subject, String content) {
        Properties properies = new Properties();
        properies.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        properies.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        properies.put("mail.smtp.auth", "true");
        properies.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properies, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMAIL_SENT, GMAIL_PASSWORD);
            }
        });

        // Compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailTo));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject(subject);
            message.setText("Your OTP to reset your password is:  " + content);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
