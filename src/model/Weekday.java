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
    private int weekID;
    private boolean omit;

    public Weekday(int id, Recipe recipe, Calendar cal, int weekID, boolean omit) {
        this.id = id;
        this.recipe = recipe;
        this.cal = cal;
        this.weekID = weekID;
        this.omit = omit;
    }

    public boolean isOmit() {
        return omit;
    }

    public void setOmit(boolean omit) {
        this.omit = omit;
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
        return weekID;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public int compareTo(Object t) {
        Weekday w = (Weekday) t;
        int value;
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this.cal.getTimeInMillis() < w.cal.getTimeInMillis()) {
            value = BEFORE;
        } else if (this.cal.getTimeInMillis() > w.cal.getTimeInMillis()) {
            value = AFTER;
        } else {
            value = EQUAL;
        }
        return value;
    }
}
