package com.msk.factory;

import com.msk.factory.products.Pizza;
import com.msk.factory.stores.ChicagoStylePizzaStore;
import com.msk.factory.stores.NYStylePizzaStore;
import com.msk.factory.stores.PizzaStore;

/*
    The Factory Method Pattern defines an interface for creating an object,
    but lets subclasses decide which class to instantiate.
    Factory Method lets a class defer instantiation to subclasses
 */

public class PizzaFranchise {

    public static void main(String[] args) {
        PizzaStore nyPizzaStore = new NYStylePizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoStylePizzaStore();

        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + ".\n");

        pizza = chicagoPizzaStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + ".\n");
    }
}
