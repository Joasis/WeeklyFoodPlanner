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
import model.Unit;

/**
 *
 * @author Jonas
 */
public class IngredientHandler {

    private final ArrayList<Ingredient> ingredientList;

    public IngredientHandler() {
        ingredientList = new ArrayList<>();
        getIngredientsFromDB();
    }
    

    public void getIngredientsFromDB() {
        try {
            ResultSet rs = Start.dbh.selectAll("ingr");
            while (rs.next()) {
                Ingredient ing = new Ingredient(rs.getInt("in_id"), rs.getString("in_name"));
                ingredientList.add(ing);
            }
        } catch (SQLException ex) {
            System.out.println("SQL FEJL " + ex);
        }
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }
}
