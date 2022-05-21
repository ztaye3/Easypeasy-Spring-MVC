package com.alemira.sit.easypeasy.services.impl;

import com.alemira.sit.easypeasy.dao.UserDao;
import com.alemira.sit.easypeasy.domain.UserDomain;
import com.alemira.sit.easypeasy.entities.Recipe;
import com.alemira.sit.easypeasy.entities.Role;
import com.alemira.sit.easypeasy.entities.User;
import com.alemira.sit.easypeasy.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {


    private static final String ROLE_USER = "USER";


    @Autowired
    private UserDao userDao;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Transactional
    @Override
    public User createUser(UserDomain userDomain) {

        User user;

        try {
            //Check if user is already exists using unique user attributes
            if (null == userDao.findUserByEmailOrPhoneNumberOrUserName(userDomain.getEmail(), userDomain.getPhoneNumber(), userDomain.getUserName())){

                //Create a new user
                user = new User(userDomain.getFirstName(), userDomain.getLastName(), userDomain.getEmail(), encoder.encode(userDomain.getPassword()), Arrays.asList(new Role(ROLE_USER)), userDomain.getUserName(),userDomain.getPhoneNumber(), Arrays.asList(new Recipe(0, "", "", "")));

                //Set user Id incrementally
                user.setId(null == userDao.findMaxId()? 0 : userDao.findMaxId() + 1);

                return userDao.save(user);
            }else {
                return null;
            }
        }catch (Exception e){
            throw e;
        }

    }

    @Transactional
    @Override
    public List<User> listAll() {
        return userDao.findAll();
    }

    @Override
    public UserDomain getById(Integer id) {
        return null;
    }

    @Override
    public UserDomain saveOrUpdate(UserDomain domainObject) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public User loadUserByUsernameCredentials(String userName) throws UsernameNotFoundException {

        User user = userDao.findUserByEmailOrPhoneNumberOrUserName(userName, userName, userName);

        if(null == user){
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userDao.findUserByEmailOrPhoneNumberOrUserName(userName, userName, userName);

        if(null == user){
            throw new UsernameNotFoundException("User not found!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapSpringRolesToAuthorities(user.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapSpringRolesToAuthorities(Collection<Role> roles){

        //This maps roles of currently logged in user to spring security for validation purpose
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
