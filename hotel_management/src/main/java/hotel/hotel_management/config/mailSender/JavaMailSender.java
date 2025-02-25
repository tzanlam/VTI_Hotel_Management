package hotel.hotel_management.config.mailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class JavaMailSender {
    @Value("${spring.mail.username}")
    private String mailAdmin;

    private final org.springframework.mail.javamail.JavaMailSender javaMailSender;

    public JavaMailSender(org.springframework.mail.javamail.JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMailFromAdmin(String to, String subject, String text, boolean isHtml) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            mimeMessageHelper.setFrom(mailAdmin);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, isHtml);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
