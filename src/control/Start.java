/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Ingredient;
import model.Recipe;
import model.Unit;
import model.Week;
import model.Weekday;

/**
 *
 * @author Jonas
 */
public class Start {

    protected static DBHandler dbh;
    protected static UnitHandler uh;
    protected static IngredientHandler ih;
    protected static RecipeHandler rh;
    protected static WeekHandler wh;
    protected static WeekdayHandler wdh;

    public Start() {
        dbh = new DBHandler();
        uh = new UnitHandler();
        ih = new IngredientHandler();
        rh = new RecipeHandler();
        wdh = new WeekdayHandler();
        wh = new WeekHandler();

        System.out.println("List of all unittypes:");
        for (Unit un : uh.getUnitList()) {
            System.out.println("\t" + un.getName() + " (" + un.getShortname() + ")");
        }
        System.out.println("\nList of all ingredients:");
        for (Ingredient in : ih.getIngredientList()) {
            System.out.println("\t" + in.getName());
        }

        System.out.println("\nList of all recipes:");
        for (Recipe rec : rh.getRecipeList()) {
            System.out.println("\t" + rec.getName());
        }

        System.out.println("\nList of all Weekdays:");
        for (Weekday weekday : wdh.getWeekdays()) {
            System.out.println("\t" + weekday.getDate());
        }

        System.out.println("\nList of all Weeks:");
        for (Week week : wh.getWeekList()) {
            System.out.println("\tWeek of year: " + week.getDate());
        }
    }
}
