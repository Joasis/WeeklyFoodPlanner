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
import java.util.LinkedList;
import model.Week;
import model.Weekday;

/**
 *
 * @author Jonas
 */
public class WeekHandler {

    private LinkedList<Week> weekList;
    private Weekday[] weekdaysToSwitch = new Weekday[2];

    public WeekHandler() {
        weekList = new LinkedList<>();
        getWeeksFromDB();
    }

    public void getWeeksFromDB() {
        try {
            ResultSet rs = ControlHandler.getDbh().selectAll("wk order by wk_date DESC");
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

    public LinkedList<Week> getWeekList() {
        return weekList;
    }
}
