package com.alemira.sit.easypeasy.controllers;

import com.alemira.sit.easypeasy.domain.UserDomain;
import com.alemira.sit.easypeasy.entities.User;
import com.alemira.sit.easypeasy.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserServices userManagementService;

    //Create new user controller
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(UserDomain user, Model model){

        User newUser = userManagementService.createUser(user);

       if(newUser != null){
           return "redirect:/login";
       }
       else{
           return "user/registration";
       }
    }

    //Get all available users in the system controller
    @RequestMapping({"/getAllUsers"})
    public String getAllUsers(Model model) {
        model.addAttribute("users", userManagementService.listAll());
        return "user/userList";
    }

    //Register a new user controller
    @RequestMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("userDomain",new UserDomain());
        return "user/registration";
    }

    //Get current user controller
    @RequestMapping({"/getUser"})
    public String getUser(Model model) {
        model.addAttribute("userDomain", getCurrentUser());
        return "user/user";
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
