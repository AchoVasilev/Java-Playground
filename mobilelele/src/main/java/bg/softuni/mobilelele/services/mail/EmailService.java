package bg.softuni.mobilelele.services.mail;

import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Locale;

@Service
public class EmailService implements IEmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final MessageSource messageSource;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender, MessageSource messageSource) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
    }

    public void sendRegistrationEmail(String userEmail, String userName, Locale preferredLocale) {
        var mimeMessage = this.javaMailSender.createMimeMessage();

        try {
            var mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("mobilele@mobilele.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject(this.getEmailSubject(preferredLocale));
            mimeMessageHelper.setText(this.generateMessageContent(preferredLocale, userName), true);

            this.javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String getEmailSubject(Locale locale) {
        return messageSource.getMessage("registration_subject", new Object[0], locale);
    }

    private String generateMessageContent(Locale locale, String userName) {
        var ctx = new Context();

        ctx.setLocale(locale);
        ctx.setVariable("userName", userName);
        return this.templateEngine.process("email/registration", ctx);
    }
}
