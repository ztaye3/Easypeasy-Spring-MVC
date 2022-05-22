package com.alemira.sit.easypeasy.utils;

import com.alemira.sit.easypeasy.entities.Recipe;
import com.alemira.sit.easypeasy.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestUtils {
    public  User getCurrentUser(){

        // recipe dummy 
        Recipe recipe = new Recipe();
        recipe.setName("Test");
        recipe.setDescription("Test recipe data");
        recipe.setPhotos("/blabla");

        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(recipe);

        // user dummy
        User user = new User();
        user.setUserName("ztaye");
        user.setEmail("ztaye@gmail.com");
        user.setFirstName("Zekarias Taye");
        user.setLastName("Hirpo");
        user.setPhoneNumber("0927702700");
        user.setRecipes(recipes);

        return user;
    }
}
