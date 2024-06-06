package com.msk.factory.stores;

import com.msk.factory.ingredients.NYPizzaIngredientFactory;
import com.msk.factory.ingredients.PizzaIngredientFactory;
import com.msk.factory.products.*;

public class NYStylePizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        // The NY Store is composed with a NY pizza ingredient factory.
        // This will be used for all NY-Style pizza.
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();


        // For each type of pizza we instantiate a new Pizza
        // and give it the factory it needs to get its ingredients.
        if (type.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        }
        else if (type.equals("veggie")) {
            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("New York Style Veggie Pizza");
        }
        else if (type.equals("clam")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("New York Style Clam Pizza");
        }
        else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza(ingredientFactory);
            pizza.setName("New York Style Pepperoni Pizza");
        }

        return pizza;
    }
}
