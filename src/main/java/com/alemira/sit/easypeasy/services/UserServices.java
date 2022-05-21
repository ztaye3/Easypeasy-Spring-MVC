package com.alemira.sit.easypeasy.services;

import com.alemira.sit.easypeasy.domain.UserDomain;
import com.alemira.sit.easypeasy.entities.User;
import com.alemira.sit.easypeasy.services.CRUService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserServices extends CRUService<UserDomain>, UserDetailsService {

    /**
     * This method method creates a new user during registration
     *
     *  @param user UserDomain type
     *  @return registered User
     */
    User createUser(UserDomain user);

    /**
     * This method method returns all registered users
     *
     *  @return generic list
     */

    @Override
    List<?> listAll();

    /**
     * This method method returns user by id
     *
     * @param id integer type
     * @return UserDomain
     */
    @Override
    UserDomain getById(Integer id);

    /**
     * This method update or create new user
     *
     * @param domainObject User domain type
     * @return existing or new UserDomain
     */
    @Override
    UserDomain saveOrUpdate(UserDomain domainObject);

    /**
     * This method delete user by id
     *
     * @param id integer type
     * @return void
     */
    @Override
    void delete(Integer id);

}
