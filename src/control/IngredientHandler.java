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

/**
 *
 * @author Jonas
 */
public class IngredientHandler {

    private final ArrayList<Ingredient> ingredientList;

    public IngredientHandler() throws SQLException {
        ingredientList = new ArrayList<>();
        getIngredientsFromDB();
    }

    public void getIngredientsFromDB() throws SQLException {
        ResultSet rs = ControlHandler.getDbh().selectAll("ingr");
        while (rs.next()) {
            Ingredient ing = new Ingredient(rs.getInt("in_id"), rs.getString("in_name"));
            ingredientList.add(ing);
        }
    }

    public Ingredient getIngredient(int ingredientID) {
        Ingredient ingredient = null;
        boolean ingredientFound = false;
        for (int i = 0; i < ingredientList.size() && ingredientFound == false; i++) {
            if (ingredientList.get(i).getId() == ingredientID) {
                ingredient = ingredientList.get(i);
                ingredientFound = true;
            }
        }
        return ingredient;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }
}
