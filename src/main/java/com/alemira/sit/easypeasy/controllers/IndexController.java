package com.alemira.sit.easypeasy.controllers;

import com.alemira.sit.easypeasy.entities.Recipe;
import com.alemira.sit.easypeasy.entities.User;
import com.alemira.sit.easypeasy.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class IndexController {

    @Autowired
    private UserServices userManagementService;

    @RequestMapping("/")
    public String index(Model model) {

        //Get currently logged in users data
        User user = getCurrentUser();

        Collection<Recipe> recipes = new ArrayList<>();

        for(com.alemira.sit.easypeasy.entities.Recipe recipe : user.getRecipes()){
            if(!recipe.getDescription().isEmpty())
                recipes.add(recipe);
        }

        model.addAttribute("recipes", recipes);

        model.addAttribute("user", user);
        model.addAttribute("recipeCount", user.getRecipes().size() - 1);

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    private User getCurrentUser(){

        //Get currently logged in users data
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName = null;

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }

        return userManagementService.loadUserByUsernameCredentials(userName);
    }
}
