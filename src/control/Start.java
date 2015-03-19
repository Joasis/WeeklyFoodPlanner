/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Ingredient;
import model.Unit;

/**
 *
 * @author Jonas
 */
public class Start {

    protected static DBHandler dbh;
    private UnitHandler uh;
    private IngredientHandler ih;

    public Start() {
        dbh = new DBHandler();
        uh = new UnitHandler();
        ih = new IngredientHandler();

        System.out.println("List of all unittypes:");
        for (Unit un : uh.getUnitList()) {
            System.out.println("\t" + un.getName() + " (" + un.getShortname() + ")");
        }
        System.out.println("\nList of all ingredients:");
        for (Ingredient in : ih.getIngredientList()) {
            System.out.println("\t" + in.getName());
        }
    }
}
