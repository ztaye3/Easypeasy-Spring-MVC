package com.alemira.sit.easypeasy.controllers;

import com.alemira.sit.easypeasy.domain.UserDomain;
import com.alemira.sit.easypeasy.entities.User;
import com.alemira.sit.easypeasy.services.UserServices;
import com.alemira.sit.easypeasy.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.alemira.sit.easypeasy.utils.Constant.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {
    private MockMvc mockMvc;

    // Mockito Mock Object
    @Mock 
    private UserServices userManagementService;

    // Setups up controller, and injects mock objects into it.
    @InjectMocks 
    private UserController userController;

    private TestUtils testUtils;

    @BeforeEach
    public void setup() {
        // Initializes controller and mocks
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        testUtils = new TestUtils();
    }

    @Test
    // tests that List of userDomains can be created and returned correctly
    public void testList() throws Exception {


        List<UserDomain> userDomains = new ArrayList<>();
        userDomains.add(new UserDomain());
        userDomains.add(new UserDomain());

        // Specific Mockito interaction, tell stub to return list of userDomains.
        // need to strip generics
        when(userManagementService.listAll()).thenReturn((List) userDomains);

        this.mockMvc.perform(get(GET_ALL_USER_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(GET_ALL_USER_VIEW))
                .andExpect(model().attribute("users", hasSize(2)));
    }

    @Test
    // tests create user
    public void createUser() throws Exception {


        // Stub createUser method
        when(userManagementService.createUser(any())).thenReturn(testUtils.getCurrentUser());

        this.mockMvc.perform( MockMvcRequestBuilders
                        .post(CREATE_USER_URL)
                        .content(asJsonString(new UserDomain()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(LOGIN_USER_REDIRECT_URL));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
