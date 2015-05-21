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

    public WeekHandler() throws SQLException {
        weekList = new LinkedList<>();
        getWeeksFromDB();
    }

    public void getWeeksFromDB() throws SQLException {
            ResultSet rs = ControlHandler.getDbh().selectAll("wk order by wk_date DESC");
            while (rs.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(rs.getLong("wk_date"));
                int weekID = rs.getInt("wk_id");
                Week week = new Week(weekID, cal, rs.getInt("wk_rare"), gettWeekdays(weekID));
                weekList.add(week);
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

    public Week getWeek(int weekNumber) {
        boolean found = false;
        Week week = null;
        for (int i = 0; i < weekList.size() && !found; i++) {
            if (weekList.get(i).getDate() == weekNumber) {
                week = weekList.get(i);
                found = true;
            }
        }
        return week;
    }

    public LinkedList<Week> getWeekList() {
        return weekList;
    }
}
