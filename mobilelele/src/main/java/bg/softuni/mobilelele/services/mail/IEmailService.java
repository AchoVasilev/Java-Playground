package bg.softuni.mobilelele.services.mail;

public interface IEmailService {
    void sendRegistrationEmail(String userEmail, String userName);
}
