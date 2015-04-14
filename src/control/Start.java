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
import view.GUI;

/**
 *
 * @author Jonas
 */
public class Start {

    public Start() {
        ControlHandler ch = new ControlHandler();

        System.out.println("List of all unittypes:");
        for (Unit un : ch.getUh().getUnitList()) {
            System.out.println("\t" + un.getName() + " (" + un.getShortname() + ")");
        }
        System.out.println("\nList of all ingredients:");
        for (Ingredient in : ch.getIh().getIngredientList()) {
            System.out.println("\t" + in.getName());
        }

        System.out.println("\nList of all recipes:");
        for (Recipe rec : ch.getRh().getRecipeList()) {
            System.out.println("\t" + rec.getName());
        }

        System.out.println("\nList of all Weekdays:");
        for (Weekday weekday : ch.getWdh().getWeekdays()) {
            System.out.println("\t" + weekday.getDate());
        }

        System.out.println("\nList of all Weeks:");
        for (Week week : ch.getWh().getWeekList()) {
            System.out.println("\tWeek of year: " + week.getDate());
        }
        GUI gui = new GUI(ch);
        gui.setVisible(true);
    }
}
