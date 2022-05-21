package com.alemira.sit.easypeasy.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Recipe {


    private int id;
    private String description;
    private String name;
    private String photos;

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe() {
    }

    public Recipe(int id, String description, String name, String photos) {
        super();
        this.id = id;
        this.description = description;
        this.name = name;
        this.photos = photos;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
