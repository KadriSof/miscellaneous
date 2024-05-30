package com.msk.factory.stores;

import com.msk.factory.products.Pizza;
import com.msk.factory.products.newyork.NYStyleCheesePizza;
import com.msk.factory.products.newyork.NYStyleClamPizza;
import com.msk.factory.products.newyork.NYStylePepperoniPizza;
import com.msk.factory.products.newyork.NYStyleVeggiePizza;

public class NYStylePizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        if (type.equalsIgnoreCase("cheese")) {
            return new NYStyleCheesePizza();
        } else if (type.equalsIgnoreCase("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else if (type.equalsIgnoreCase("veggie")) {
            return new NYStyleVeggiePizza();
        } else if (type.equalsIgnoreCase("clam")) {
            return new NYStyleClamPizza();
        } else return null;
    }
}
