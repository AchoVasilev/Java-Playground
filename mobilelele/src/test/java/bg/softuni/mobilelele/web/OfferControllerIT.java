package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.utils.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private OfferEntity testOffer;
    private OfferEntity testAdminOffer;

    @BeforeEach
    public void setUp() {
        var testUser = this.testDataUtils.createTestUser("user@user.com");
        var testAdmin = this.testDataUtils.createTestAdmin("admin@admin.com");

        var testModel = this.testDataUtils.createTestModel(this.testDataUtils.createTestBrand());

        this.testOffer = this.testDataUtils.createTestOffer(testUser, testModel);
        this.testAdminOffer = this.testDataUtils.createTestOffer(testAdmin, testModel);
    }

    @AfterEach
    public void tearDown() {
        this.testDataUtils.cleanUp();
    }

    @Test
    public void testDeleteByAnonymousUser() throws Exception {
        this.mockMvc.perform(delete("/offers/{id}", this.testOffer.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", roles = {"ADMIN", "USER"})
    public void testDeleteByAdmin() throws Exception {
        this.mockMvc.perform(delete("/offers/{id}", this.testOffer.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/all"));
    }

    @Test
    @WithMockUser(username = "user@user.com")
    public void testDeleteByOwner() throws Exception {
        this.mockMvc.perform(delete("/offers/{id}", this.testOffer.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/all"));
    }

    @Test
    @WithMockUser(username = "user@user.com")
    public void testDeleteByNotOwner() throws Exception {
        this.mockMvc.perform(delete("/offers/{id}", this.testAdminOffer.getId())
                        .with(csrf()))
                .andExpect(status().isForbidden())
                .andExpect(view().name("redirect:/offers/all"));
    }
}
