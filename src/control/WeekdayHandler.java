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
import javax.swing.JOptionPane;
import model.Recipe;
import model.Weekday;
import view.GUI;

/**
 *
 * @author Jonas
 */
public class WeekdayHandler {

    private ArrayList<Weekday> weekdays;
    private Weekday selectedWeekday;

    public WeekdayHandler() throws SQLException {
        selectedWeekday = null;
        weekdays = new ArrayList<>();
        getWeekDaysFromDB();
    }

    public void getWeekDaysFromDB() throws SQLException {
        ResultSet rs = ControlHandler.getDbh().selectAll("wkday order by wkd_date");
        while (rs.next()) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(rs.getLong("wkd_date"));
            Recipe recipe = ControlHandler.getRh().getRecipe(rs.getInt("fk_recipe"));
            Weekday weekday = new Weekday(rs.getInt("wkd_id"), recipe, cal, rs.getInt("fk_week"), rs.getBoolean("wkd_omit"));
            weekdays.add(weekday);
        }
    }

    public ArrayList<Weekday> getWeekdays() {
        return weekdays;
    }

    public int switchDays(Weekday wd) {
        int swap = 0;
        if (selectedWeekday == null) {
            selectedWeekday = wd;
        } else if (selectedWeekday == wd) {
            swap = 1;
            selectedWeekday = null;
        } else {
            Calendar selectedWeekCal = selectedWeekday.getDateByCal();
            try {
                selectedWeekday.setCal(wd.getDateByCal());
                wd.setCal(selectedWeekCal);
                ControlHandler.getDbh().updateWeekdayDate(wd);
                ControlHandler.getDbh().updateWeekdayDate(selectedWeekday);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Indlæsning af ugeplan mislykkedes\nKunne ikke etablere forbindelse til databasen", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
                GUI.decorateUI("Luk", "");
            }
            selectedWeekday = null;
            swap = 2;
        }
        return swap;
    }
}
