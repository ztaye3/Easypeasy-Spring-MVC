package com.alemira.sit.easypeasy.dao;

import com.alemira.sit.easypeasy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * This method fetch user by user name, phone number or email
     *
     * @param email String type
     *
     * @param phoneNumber String type
     *
     * @param userName String type
     *
     * @return User
     */
    User findUserByEmailOrPhoneNumberOrUserName(String email, String phoneNumber, String userName);

    /**
     * This method fetch the max user id column value from user table
     *
     * @return Integer
     */
    @Query("select max(u.id) from User u")
     Integer findMaxId();


}
