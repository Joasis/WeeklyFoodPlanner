/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import model.Ingredient;
import model.IngredientAmount;
import model.Recipe;
import model.Unit;

/**
 *
 * @author Jonas
 */
public class RecipeHandler {

    private ArrayList<Recipe> recipeList;
    private ArrayList<Recipe> addedRecipes;
    public static final int NO_INGREDIENTS = -866;

    public RecipeHandler() {
        getRecipesFromDB();
    }

    public void getRecipesFromDB() {
        clearAddedRecipes();
        recipeList = new ArrayList<>();
        try {
            ResultSet rs = ControlHandler.getDbh().selectAll("amount inner join recipe on fk_recipe = re_id");

            ArrayList<IngredientAmount> ingList = new ArrayList<>();;
            IngredientAmount ingAm;
            Ingredient ing;
            Unit unit;

            int currentID = 0;
            while (rs.next()) {
                if (currentID != rs.getInt("re_id")) {
                    ingList = new ArrayList<>();
                    Recipe rec = new Recipe(rs.getInt("re_id"), rs.getString("re_name"), rs.getString("re_description"), rs.getInt("re_portions"), rs.getInt("re_cooktime"), rs.getBoolean("re_active"), ingList);
                    recipeList.add(rec);
                }
                if (rs.getDouble("am_amount") != NO_INGREDIENTS) {
                    unit = ControlHandler.getUh().getUnit(rs.getInt("fk_am_unittype"));
                    ing = ControlHandler.getIh().getIngredient(rs.getInt("fk_ingr"));
                    ingAm = new IngredientAmount(unit, ing, rs.getDouble("am_amount"));
                    ingList.add(ingAm);
                }
                currentID = rs.getInt("re_id");
            }
        } catch (SQLException ex) {
            System.out.println("SQL FEJL " + ex);
        }
    }

    public Recipe getRecipe(int recipeID) {
        Recipe recipe = null;
        boolean recipeFound = false;
        for (int i = 0; i < recipeList.size() && recipeFound == false; i++) {
            if (recipeList.get(i).getId() == recipeID) {
                recipe = recipeList.get(i);
                recipeFound = true;
            }
        }
        return recipe;
    }

    public boolean isIngredientFound(Recipe recipe, IngredientAmount ingAm) {
        boolean ingredientFound = false;
        for (int i = 0; i < recipe.getIngredientList().size() && !ingredientFound; i++) {
            if (ingAm.getIngredient().getName().equals(recipe.getIngredientList().get(i).getIngredient().getName()) && ingAm.getUnit() == recipe.getIngredientList().get(i).getUnit()) {
                ingredientFound = true;
            }
        }
        return ingredientFound;
    }

    public ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    public int getActiveRecipeListsize() {
        ArrayList<Recipe> activeList = new ArrayList<>();
        for (Recipe rp : getRecipeList()) {
            if (rp.isActive()) {
                activeList.add(rp);
            }
        }
        return activeList.size();
    }

    public Recipe getRandomRecipe(int n) {
        Recipe recipe = null;
        if (addedRecipes.size() < 7) {
            for (int i = 0; addedRecipes.size() < 7; i++) {
                recipe = recipeList.get((int) (Math.random() * recipeList.size()));
                if (!addedRecipes.contains(recipe) && recipe.isActive()) {
                    addedRecipes.add(recipe);
                }
            }
            recipe = addedRecipes.get(n);
        } else {
            recipe = addedRecipes.get(n);
        }
        return recipe;
    }

    public void clearAddedRecipes() {
        addedRecipes = new ArrayList<>();
    }

    public boolean isRecipeFound(String recipeName) {
        boolean recipeFound = false;
        for (int i = 0; i < recipeList.size() && !recipeFound; i++) {
            if (recipeList.get(i).getName().equalsIgnoreCase(recipeName) && recipeList.get(i).isActive()) {
                recipeFound = true;
            }
        }
        return recipeFound;
    }
}
