package com.msk.factory.stores;

import com.msk.factory.products.CheesePizza;
import com.msk.factory.products.Pizza;
import com.msk.factory.products.chicago.ChicagoStyleCheesePizza;
import com.msk.factory.products.chicago.ChicagoStyleClamPizza;
import com.msk.factory.products.chicago.ChicagoStylePepperoniPizza;
import com.msk.factory.products.chicago.ChicagoStyleVeggiePizza;

public class ChicagoStylePizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        if (type.equalsIgnoreCase("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else if (type.equalsIgnoreCase("pepperoni")) {
            return new ChicagoStylePepperoniPizza();
        } else if (type.equalsIgnoreCase("clam")) {
            return new ChicagoStyleClamPizza();
        } else if (type.equalsIgnoreCase("veggie")) {
            return new ChicagoStyleVeggiePizza();
        } else return null;
    }
}
