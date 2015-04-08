/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author Jonas
 */
public class Week {

    private int id;
    private Calendar cal;
    private int rarity;
    private Weekday[] weekdays;

    public Week(int id, Calendar cal, int rarity, Weekday[] weekdays) {
        this.id = id;
        this.cal = cal;
        this.rarity = rarity;
        this.weekdays = weekdays;
    }

    public int getId() {
        return id;
    }

    public int getDate() {
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public int getRarity() {
        return rarity;
    }

    public Weekday[] getWeekdays() {
        return weekdays;
    }

    @Override
    public String toString() {
        return getDate() + " (" + cal.get(Calendar.YEAR) + ")";
    }
}
