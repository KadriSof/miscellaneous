package com.msk.factory.ingredients;

import com.msk.factory.ingredients.primary.*;

// An Abstract Factory for creating a family of products.
// That allows us to implement a variety of factories
// that produce products meant for different contexts.
public interface PizzaIngredientFactory {
    
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClams();
}
