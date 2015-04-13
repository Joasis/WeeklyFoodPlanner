/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jonas
 */
public class Unit {

    private int id;
    private String name;
    private String shortname;

    public Unit(int id, String name, String shortname) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortname() {
        return shortname;
    }

    public String toString() {
        return getName();
    }
}
