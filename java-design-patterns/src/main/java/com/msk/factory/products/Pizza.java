package com.msk.factory.products;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    protected String name;
    protected String dough;
    protected String sauce;
    protected List<String> toppings = new ArrayList<String>();

    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings: ");
        for (String topping : toppings) {
            System.out.println("   " + topping);
        }
    };

    public void bake() {
        System.out.println("Baking for 25 minutes at 350°");
    };

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    };

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    };

    public String getName() {
        return name;
    }
}
