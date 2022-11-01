package bg.softuni.mobilelele.services.mail;

import java.util.Locale;

public interface IEmailService {
    void sendRegistrationEmail(String userEmail, String userName, Locale preferredLocale);
}
