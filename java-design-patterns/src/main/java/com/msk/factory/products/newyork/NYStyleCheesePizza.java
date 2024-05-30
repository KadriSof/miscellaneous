package com.msk.factory.products.newyork;

import com.msk.factory.products.CheesePizza;

public final class NYStyleCheesePizza extends CheesePizza {

    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
    }
}
