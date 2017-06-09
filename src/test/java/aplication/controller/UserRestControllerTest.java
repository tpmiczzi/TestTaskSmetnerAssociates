package aplication.controller;

import aplication.BaseTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserRestControllerTest extends BaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void validate_default_get_address() throws Exception {
        String defaultUrl = "/userservice/register";
        mockMvc.perform(get(defaultUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstName").value("Some first name"))
                .andExpect(jsonPath("$.lastName").value("The last name"))
                .andExpect(jsonPath("$.userName").value("The user name"));
    }

    @Test
    public void validate_user_get_address() throws Exception {
        String userUrl = "/userservice/register?firstName=Pavel&lastName=Tula&userName=tpm&plainTextPassword=12345";
        mockMvc.perform(get(userUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstName").value("Pavel"))
                .andExpect(jsonPath("$.lastName").value("Tula"))
                .andExpect(jsonPath("$.userName").value("tpm"));
    }

    @Test
    public void validate_bed_address() throws Exception{
        String badUrl = "/userservice/badregister";
        mockMvc.perform(get(badUrl))
                .andExpect(status().isNotFound());
    }
}
