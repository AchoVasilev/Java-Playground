package bg.softuni.mobilelele.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;

import java.util.Locale;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerTestWithGreenmail {
    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.port}")
    private Integer mailPort;
    @Value("mobilele@example.com")
    private String userName;
    @Value("12345")
    private String password;

    private GreenMail greenMail;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.greenMail = new GreenMail(new ServerSetup(mailPort, mailHost, "smtp"));
        greenMail.setUser(userName, password);
        greenMail.start();
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testRegistration() throws Exception {
        this.mockMvc.perform(post("/users/register")
                        .param("email", "pesho@abv.bg")
                        .param("firstName", "Pesho")
                        .param("lastName", "Peshev")
                        .param("password", "12345")
                        .param("confirmPassword", "12345")
                        .cookie(new Cookie("lang", Locale.GERMAN.getLanguage()))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        var receivedMessages = this.greenMail.getReceivedMessages();

        Assertions.assertEquals(1, receivedMessages.length);

        var mimeMessage = receivedMessages[0];
        Assertions.assertTrue(mimeMessage.getContent().toString().contains("Pesho Peshev"));
    }
}
