package de.javaffm221.truck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TruckIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void expect401_withoutValidUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/trucks"))
                .andExpect(status().is(401));
    }

    @Test
    @WithMockUser
    void expect200_withValidUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/trucks"))
                .andExpect(status().is(200))
                .andExpect(content().json("[]"));
    }

    @Test
    @WithMockUser(roles = {"BASIC"})
    void expect403_withNotAuthorisedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/trucks"))
                .andExpect(status().is(403));
    }
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void expect400_withAuthorisedUserAndWrongBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/trucks"))
                .andExpect(status().is(400));
    }
}