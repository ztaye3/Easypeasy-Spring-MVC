package com.alemira.sit.easypeasy.controllers;

import com.alemira.sit.easypeasy.domain.UserDomain;
import com.alemira.sit.easypeasy.services.UserServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserDomainControllerTest {

    // Mockito Mock Object
    @Mock 
    private UserServices userService;

    // Setups up controller, and injects mock objects into it.
    @InjectMocks 
    private UserController userController;

    @Test
    // tests that List of userDomains can be created and returned correctly
    public void testList() throws Exception {
        // Initializes controller and mocks
        MockitoAnnotations.openMocks(this); 
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        List<UserDomain> userDomains = new ArrayList<>();
        userDomains.add(new UserDomain());
        userDomains.add(new UserDomain());

        // Specific Mockito interaction, tell stub to return list of userDomains.
        // need to strip generics
        when(userService.listAll()).thenReturn((List) userDomains); 
        mockMvc.perform(MockMvcRequestBuilders.get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attribute("userDomains", hasSize(2)));
    }

    @Test
    // tests that one particular user is created and requested correctly by id
    public void testUser() throws Exception {
        Integer id = 1;
        // Initializes controller and mocks
        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        // Tell Mockito stub to return new user for ID 1
        when(userService.getById(id)).thenReturn(new UserDomain());

        mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/user"))
                .andExpect(model().attribute("user", instanceOf(UserDomain.class)));

    }

}
