package com.alemira.sit.easypeasy.services;

import com.alemira.sit.easypeasy.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface CRUService<T> {

    /**
     * This method method returns available data
     *
     *  @return generic list
     */

    List<?> listAll();

    /**
     * This method method returns by id
     *
     * @param id integer type
     * @return current generic object
     */
    T getById(Integer id);

    /**
     * This method update or create new object
     *
     * @param domainObject generic type
     * @return existing or new object
     */
    T saveOrUpdate(T domainObject);

    /**
     * This method delete object by id
     *
     * @param id integer type
     * @return void
     */
    void delete(Integer id);

    /**
     * This method loads user by user name, email or phone number
     *
     * @param s String type
     * @return User
     */

    User loadUserByUsernameCredentials(String s) throws UsernameNotFoundException;
}
