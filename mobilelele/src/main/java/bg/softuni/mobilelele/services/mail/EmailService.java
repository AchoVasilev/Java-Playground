package bg.softuni.mobilelele.services.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Service
public class EmailService implements IEmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public void sendRegistrationEmail(String userEmail, String userName) {
        var mimeMessage = this.javaMailSender.createMimeMessage();

        try {
            var mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("mobilele@mobilele.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject("Welcome to Mobilele");
            mimeMessageHelper.setText(this.generateMessageContent(userName), true);

            this.javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String generateMessageContent(String userName) {
        var ctx = new Context();
        ctx.setVariable("userName", userName);
        return this.templateEngine.process("email/registration", ctx);
    }
}
