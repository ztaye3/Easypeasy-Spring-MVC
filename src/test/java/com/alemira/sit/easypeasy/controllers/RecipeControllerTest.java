package com.alemira.sit.easypeasy.controllers;

import com.alemira.sit.easypeasy.services.RecipeServices;
import com.alemira.sit.easypeasy.services.UserServices;
import com.alemira.sit.easypeasy.utils.TestUtils;
import com.alemira.sit.easypeasy.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.alemira.sit.easypeasy.utils.Constant.GET_ALL_RECIPE_URL;
import static com.alemira.sit.easypeasy.utils.Constant.GET_RECIPE_URL;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RecipeControllerTest {
    private MockMvc mockMvc;

    private TestUtils testUtils;

    @Mock
    private RecipeServices recipeServices;

    @Mock
    private UserServices userManagementService;
    @Mock
    Utils utils;

    @InjectMocks
    private RecipeController recipeController;



    @BeforeEach
    void setUp(){
        // Initializes controller and mocks
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        this.testUtils = new TestUtils();
    }

    @Test
    public void testListOfRecipes() throws Exception {
        when(utils.getCurrentUser()).thenReturn(testUtils.getCurrentUser());
        this.mockMvc.perform(get(GET_ALL_RECIPE_URL))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRecipe() throws Exception {
        when(utils.getCurrentUser()).thenReturn(testUtils.getCurrentUser());
        this.mockMvc.perform(get(GET_RECIPE_URL + "/1"))
                .andExpect(status().isOk());
    }
}
