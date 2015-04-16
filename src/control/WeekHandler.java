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
import model.Week;
import model.Weekday;

/**
 *
 * @author Jonas
 */
public class WeekHandler {

    private ArrayList<Week> weekList;
    private Weekday[] weekdaysToSwitch = new Weekday[2];
    private Weekday selectedWeekday;

    public WeekHandler() {
        selectedWeekday = null;
        weekList = new ArrayList<>();
        getWeeksFromDB();
    }

    public void getWeeksFromDB() {
        try {
            ResultSet rs = ControlHandler.getDbh().selectAll("wk");
            while (rs.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rs.getLong("wk_date"));
                int weekID = rs.getInt("wk_id");
                Week week = new Week(weekID, cal, rs.getInt("wk_rare"), gettWeekdays(weekID));
                weekList.add(week);
            }
        } catch (SQLException ex) {
            System.out.println("SQL FEJL " + ex);
        }
    }

    public Weekday[] gettWeekdays(int weekID) {
        Weekday[] weekdaysForWeek = new Weekday[7];
        int day = 0;
        for (Weekday weekday : ControlHandler.getWdh().getWeekdays()) {
            if (weekday.getWeek() == weekID) {
                weekdaysForWeek[day] = weekday;
                day++;
            }
        }
        return weekdaysForWeek;
    }

    public ArrayList<Week> getWeekList() {
        return weekList;
    }

    public int swicthDays(Weekday wd) {
        int result = -1;
        if (weekdaysToSwitch[0] == null) {
            result = 0;
            weekdaysToSwitch[0] = wd;
        } else if (weekdaysToSwitch[1] == null) {
            result = 1;
            weekdaysToSwitch[1] = wd;
            Calendar tempDate = weekdaysToSwitch[0].getDateByCal();
            weekdaysToSwitch[0].setDateByCal(weekdaysToSwitch[1].getDateByCal());
            weekdaysToSwitch[1].setDateByCal(tempDate);
            weekdaysToSwitch[0] = null;
            weekdaysToSwitch[1] = null;
        }
        return result;
    }
//    
//    public int switchDays(Weekday wd) {
//        int swap = 0;
//        if (selectedWeekday == null) {
//            selectedWeekday = wd;
//        } else if (selectedWeekday == wd) {
//            swap = 1;
//            selectedWeekday = null;
//        } else {
//            Calendar selectedWeekCal = selectedWeekday.getDateByCal();
//            Calendar swapWeekCal = wd.getDateByCal();
//
//            selectedWeekday.setCal(swapWeekCal);
//            wd.setCal(selectedWeekCal);
//            selectedWeekday = null;
//            swap = 2;
//        }
//        return swap;
//    }
}
