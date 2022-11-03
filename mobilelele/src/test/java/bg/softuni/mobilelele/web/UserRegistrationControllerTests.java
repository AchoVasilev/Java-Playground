package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.services.mail.IEmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;

import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEmailService emailService;

    @Test
    void testRegistrationPageShow() throws Exception {
        this.mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-register"));
    }

    @Test
    void testUserRegistration() throws Exception {
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

        verify(this.emailService)
                .sendRegistrationEmail("pesho@abv.bg", "Pesho Peshev", Locale.GERMAN);
    }
}
