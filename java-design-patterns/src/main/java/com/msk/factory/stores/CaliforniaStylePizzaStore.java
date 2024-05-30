package com.msk.factory.stores;

import com.msk.factory.products.Pizza;
import com.msk.factory.products.california.CaliforniaStyleCheesePizza;
import com.msk.factory.products.california.CaliforniaStyleClamPizza;
import com.msk.factory.products.california.CaliforniaStylePepperoniPizza;
import com.msk.factory.products.california.CaliforniaStyleVeggiePizza;

public class CaliforniaStylePizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        if (type.equalsIgnoreCase("cheese")) {
            return new CaliforniaStyleCheesePizza();
        } else if (type.equalsIgnoreCase("pepperoni")) {
            return new CaliforniaStylePepperoniPizza();
        } else if (type.equalsIgnoreCase("veggie")) {
            return new CaliforniaStyleVeggiePizza();
        } else if (type.equalsIgnoreCase("clam")) {
            return new CaliforniaStyleClamPizza();
        } else return null;
    }
}
