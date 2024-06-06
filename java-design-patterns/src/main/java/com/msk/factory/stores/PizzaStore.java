package com.msk.factory.stores;

import com.msk.factory.products.Pizza;

/*
    The Factory Method Pattern encapsulates object creation
    by letting subclasses decide what objects to create.
 */

public abstract class PizzaStore {

    // Force the usage of this method by the different stores for consistency.
    final public Pizza orderPizza(String type) {
        Pizza pizza;

        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    // Abstract factory method.
    protected abstract Pizza createPizza(String type);
}

/*
    The Abstract Factory Pattern provides an interface for creating
    families of related or dependent objects without specifying their
    concrete classes.
 */