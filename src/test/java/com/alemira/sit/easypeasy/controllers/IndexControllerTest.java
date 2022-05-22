package com.alemira.sit.easypeasy.controllers;

import com.alemira.sit.easypeasy.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.alemira.sit.easypeasy.utils.Constant.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IndexControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserServices userManagementService;

    @InjectMocks
    private IndexController indexController;


    @BeforeEach
    public void setup() {
        // Initializes controller and mocks
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }
    @Test
    public void testLogin() throws Exception {

        this.mockMvc.perform(get(LOGIN_USER_URL))
                .andExpect(status().isOk());
    }
}
