package de.neuefische.backend.appuser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppUserControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getLoggedInUser_shouldReturnUserDetails_whenUserIsLoggedIn() throws Exception {
        mockMvc.perform(get("/api/users/me"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("loggedInUser"));
    }

    @Test
    void login_shouldAllowUserToLogin() throws Exception {
        mockMvc.perform(post("/api/users/login"))
                .andExpect(status().isOk());
    }

    @Test
    void register_shouldCreateNewUser_whenValidInput() throws Exception {
        AppUserRegister newUser = new AppUserRegister("newUser", "password");

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("newUser"));
    }

    @Test
    void logout_shouldInvalidateSession_whenUserLogsOut() throws Exception {
        mockMvc.perform(post("/api/users/logout"))
                .andExpect(status().isNoContent());
    }


}
