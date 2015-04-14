/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Jonas
 */
public class ControlHandler {

    private static DBHandler dbh;
    private static UnitHandler uh;
    private static IngredientHandler ih;
    private static RecipeHandler rh;
    private static WeekHandler wh;
    private static WeekdayHandler wdh;

    public ControlHandler() {
        dbh = new DBHandler();
        uh = new UnitHandler();
        ih = new IngredientHandler();
        rh = new RecipeHandler();
        wdh = new WeekdayHandler();
        wh = new WeekHandler();
    }

    public static DBHandler getDbh() {
        return dbh;
    }

    public static UnitHandler getUh() {
        return uh;
    }

    public static IngredientHandler getIh() {
        return ih;
    }

    public static RecipeHandler getRh() {
        return rh;
    }

    public static WeekHandler getWh() {
        return wh;
    }

    public static WeekdayHandler getWdh() {
        return wdh;
    }
}
