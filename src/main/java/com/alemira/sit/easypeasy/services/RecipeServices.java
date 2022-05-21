package com.alemira.sit.easypeasy.services;

import com.alemira.sit.easypeasy.entities.Recipe;

public  interface RecipeServices {

    /**
     * This method adds recipe to a user
     *
     * @param userName String type
     *
     * @param recipe Recipe type
     *
     * @return void
     */
    void addRecipe(String userName, Recipe recipe);

    /**
     * This method deletes recipe from a user
     *
     * @param userName String type
     *
     * @param id integer type
     *
     * @return void
     */
    void deleteRecipe(String userName, Integer id);
}
