/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Jonas
 */
public class Weekday implements Comparable {

    private int id;
    private Recipe recipe;
    private Calendar cal;
    private int weekNumber;
    private boolean active;

    public Weekday(int id, Recipe recipe, Calendar cal, int weekNumber, boolean active) {
        this.id = id;
        this.recipe = recipe;
        this.cal = cal;
        this.weekNumber = weekNumber;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getWeekName() {
        String weekName;
        weekName = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        weekName = Character.toUpperCase(weekName.charAt(0)) + weekName.substring(1);
        return weekName;
    }

    public String getDate() {
        long time = cal.getTimeInMillis() / 1000;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        String dayZero = "";
        String monthZero = "";
        if (day < 10) {
            dayZero = "0";
        }
        if (month < 10) {
            monthZero = "0";
        }
        String date = dayZero + day + "-" + monthZero + month + "-" + year;
        return date;
    }

    public Calendar getDateByCal() {
        return cal;
    }

    public void setDateByCal(Calendar cal) {
        this.cal = cal;
    }

    public int getWeek() {
        return weekNumber;
    }

    @Override
    public int compareTo(Object t) {
        Weekday w = (Weekday) t;
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this.cal.getTimeInMillis() < w.cal.getTimeInMillis()) {
            return BEFORE;
        } else if (this.cal.getTimeInMillis() > w.cal.getTimeInMillis()) {
            return AFTER;
        } else {
            return EQUAL;
        }
    }
}
