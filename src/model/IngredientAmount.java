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
public class IngredientAmount {

    private Unit unit;
    private Ingredient ingredient;
    private double amount;

    public IngredientAmount(Unit unit, Ingredient ingredient, double amount) {
        this.unit = unit;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return ingredient.getName();
    }
}
