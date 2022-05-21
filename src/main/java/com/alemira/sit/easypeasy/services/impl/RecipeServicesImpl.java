package com.alemira.sit.easypeasy.services.impl;

import com.alemira.sit.easypeasy.dao.UserDao;
import com.alemira.sit.easypeasy.entities.Recipe;
import com.alemira.sit.easypeasy.entities.User;
import com.alemira.sit.easypeasy.services.RecipeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class RecipeServicesImpl implements RecipeServices {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void addRecipe(String userName, Recipe recipe) {

        User user = userDao.findUserByEmailOrPhoneNumberOrUserName(userName, userName, userName);

        if(null != user){

            Collection<Recipe> recipes = new ArrayList<>();
            int count = 0;

            //Copy existing user recipes
            for(Recipe item : user.getRecipes()){
                recipes.add(item);
                count++;
            }

            Recipe newRecipe = new Recipe();

            //Extend the new recipe to the existing recipes
            newRecipe.setId(count);
            newRecipe.setDescription(recipe.getDescription());
            newRecipe.setName(recipe.getName());
            newRecipe.setPhotos(recipe.getPhotos());

            recipes.add(newRecipe);

            user.setRecipes(recipes);

            userDao.save(user);
        }
    }

    @Override
    public void deleteRecipe(String userName, Integer id) {

        User user = userDao.findUserByEmailOrPhoneNumberOrUserName(userName, userName, userName);

        if(null != user){

            Collection<Recipe> recipes = new ArrayList<>();

            //Find recipe based on provided Id
            for(Recipe item : user.getRecipes()){
                if(item.getId() != id)
                    recipes.add(item);
            }

            user.setRecipes(recipes);

            userDao.save(user);
        }
    }
}
