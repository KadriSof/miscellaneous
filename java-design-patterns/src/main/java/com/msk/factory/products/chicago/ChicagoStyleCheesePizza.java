package com.msk.factory.products.chicago;

import com.msk.factory.products.CheesePizza;
import com.msk.factory.products.Pizza;

public class ChicagoStyleCheesePizza extends CheesePizza {

    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";

        toppings.add("Shredded Mozzarella Cheese");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }

}
