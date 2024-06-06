package com.msk.factory.products;

import com.msk.factory.ingredients.primary.*;

import java.util.Arrays;

public abstract class Pizza {
    protected String name;

    // Each pizza holds a set of ingredients that are used in its preparation.
    protected Dough dough;
    protected Sauce sauce;
    protected Veggies[] veggies;
    protected Cheese cheese;
    protected Pepperoni pepperoni;
    protected Clams clam;

    // This is where we are going to collect the ingredients needed for this pizza,
    // which of course will come from the ingredient factory.
    public abstract void prepare();

    public void bake() {
        System.out.println("Baking for 25 minutes at 350°");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", dough=" + dough +
                ", sauce=" + sauce +
                ", veggies=" + Arrays.toString(veggies) +
                ", cheese=" + cheese +
                ", pepperoni=" + pepperoni +
                ", clam=" + clam +
                '}';
    }
}
