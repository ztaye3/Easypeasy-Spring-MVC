package com.alemira.sit.easypeasy.controllers;

import com.alemira.sit.easypeasy.entities.Recipe;
import com.alemira.sit.easypeasy.entities.User;
import com.alemira.sit.easypeasy.services.RecipeServices;
import com.alemira.sit.easypeasy.services.UserServices;
import com.alemira.sit.easypeasy.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Controller
public class RecipeController {

    private final static String FILE_DIRECTORY = "assets/images/";

    @Autowired
    private RecipeServices recipeServices;

    @Autowired
    private UserServices userManagementService;

    @Autowired
    private Utils utils;

    //Get all user recipes controller
    @RequestMapping("/recipes")
    public String listOfRecipes(Model model) {


        Collection<Recipe> recipes = new ArrayList<>();
        User user = utils.getCurrentUser();

        //Loop over all user recipes
        for(Recipe recipe : user.getRecipes()){
            if(!recipe.getDescription().isEmpty() && !recipe.getName().isEmpty())
                recipes.add(recipe);
        }

        model.addAttribute("recipes", recipes);
        return "recipes";
    }

    //Get single recipes by id
    @RequestMapping("/recipe/{id}")
    public String getRecipe(@PathVariable Integer id , Model model) {

        User user = utils.getCurrentUser();

        Recipe currentRecipe = new Recipe();

        //Loop over all user recipes and select a particular recipe using id
        for(Recipe recipe : user.getRecipes()){
            if(recipe.getId() == id)
                currentRecipe = recipe;
        }

        model.addAttribute("recipe", currentRecipe);
        return "recipe";
    }

    //Create a new recipe
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new com.alemira.sit.easypeasy.entities.Recipe());
        return "recipeform";
    }

    //Register a new recipe controller
    @RequestMapping(value = "/recipe", method = RequestMethod.POST)
    public String saveOrUpdateRecipe(Recipe recipe, @RequestParam(value = "file", required = false) MultipartFile file) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName = null;

        //Get current user's user name
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
            }

        //Extract file name from java File
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        recipe.setPhotos(FILE_DIRECTORY + fileName);

        recipeServices.addRecipe(userName, recipe);

        return "redirect:/recipes";

    }

    //Delete recipe using id controller
    @RequestMapping("/recipe/delete/{id}")
    public String delete(@PathVariable Integer id) {

        User user = utils.getCurrentUser();

        recipeServices.deleteRecipe(user.getUserName(), id);

        return "redirect:/recipes";
    }

}
