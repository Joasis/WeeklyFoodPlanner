/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import model.Recipe;
import model.Weekday;

/**
 *
 * @author Jonas
 */
public class WeekdayHandler {

    private ArrayList<Weekday> weekdays;

    public WeekdayHandler() {
        weekdays = new ArrayList<>();
        getWeekDaysFromDB();
    }

    public void getWeekDaysFromDB() {
        try {
            ResultSet rs = Start.dbh.selectAll("wkday");
            while (rs.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rs.getLong("wkd_date"));
                Recipe recipe = Start.rh.getRecipe(rs.getInt("fk_recipe"));
                Weekday weekday = new Weekday(rs.getInt("wkd_id"), recipe, cal, rs.getInt("fk_week"));
                weekdays.add(weekday);
            }
        } catch (SQLException ex) {
            System.out.println("SQL FEJL " + ex);
        }
    }

    public ArrayList<Weekday> getWeekdays() {
        return weekdays;
    }
}
