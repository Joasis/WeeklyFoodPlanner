/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public RecipeHandler() {
        getRecipesFromDB();
    }

    public void getRecipesFromDB() {
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
                unit = ControlHandler.getUh().getUnit(rs.getInt("fk_am_unittype"));
                ing = ControlHandler.getIh().getIngredient(rs.getInt("fk_ingr"));
                ingAm = new IngredientAmount(unit, ing, rs.getDouble("am_amount"));
                ingList.add(ingAm);
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

    public ArrayList<Recipe> cloneRecipeList() {
        ArrayList<Recipe> cloneList = new ArrayList<>();
        for (Recipe recipeClone : recipeList) {
            if (recipeClone.isActive()) {
                cloneList.add(recipeClone.cloneRecipe());
            }
        }
        return cloneList;
    }

    public boolean isRecipeFound(String recipeName) {
        boolean recipeFound = false;
        for (int i = 0; i < recipeList.size() && !recipeFound; i++) {
            if (recipeList.get(i).getName().toLowerCase().equals(recipeName.toLowerCase()) && recipeList.get(i).isActive()) {
                recipeFound = true;
            }
        }
        return recipeFound;
    }
}
