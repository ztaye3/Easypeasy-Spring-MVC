package com.alemira.sit.easypeasy.utils;

import com.alemira.sit.easypeasy.entities.User;
import com.alemira.sit.easypeasy.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    @Autowired
    private UserServices userManagementService;

    public User getCurrentUser(){

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
