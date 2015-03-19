/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Jonas
 */
public class Recipe {

    private int id;
    private String name;
    private String description;
    private ArrayList<IngredientAmount> ingredientList;

    public Recipe(int id, String name, String description, ArrayList<IngredientAmount> ingredientList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredientList = ingredientList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<IngredientAmount> getIngredientList() {
        return ingredientList;
    }

}
