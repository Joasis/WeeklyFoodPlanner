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
    private int portions;
    private int cookingtime;
    private boolean active;
    private ArrayList<IngredientAmount> ingredientList;

    public Recipe(int id, String name, String description, int portions, int cookingtime, boolean active, ArrayList<IngredientAmount> ingredientList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.portions = portions;
        this.cookingtime = cookingtime;
        this.active = active;
        this.ingredientList = ingredientList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public int getPortions() {
        return portions;
    }

    public int getCookingtime() {
        return cookingtime;
    }

    public ArrayList<IngredientAmount> getIngredientList() {
        return ingredientList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public void setCookingtime(int cookingtime) {
        this.cookingtime = cookingtime;
    }

    public void setIngredientList(ArrayList<IngredientAmount> ingredientList) {
        this.ingredientList = ingredientList;
    }
    public Recipe cloneRecipe(){
	Recipe recipeClone = new Recipe(id, name, description, portions, cookingtime, active, ingredientList);
	return recipeClone;
    }

    @Override
    public String toString() {
        return getName();
    }

}
