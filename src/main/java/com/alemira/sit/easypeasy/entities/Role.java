package com.alemira.sit.easypeasy.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// used to track professions such as (User, Chef etc.)
public class Role {

    private int id;
    private String name;


    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role() {

    }

    public Role(String name) {
        super();
        this.name = name;
    }
}
