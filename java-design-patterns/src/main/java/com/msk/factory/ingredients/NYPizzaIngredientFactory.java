package com.msk.factory.ingredients;

import com.msk.factory.ingredients.regional.*;
import com.msk.factory.ingredients.primary.*;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory{

    // An implementation of Factory Methods for creating concrete objects.
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[]{new Garlic(), new Onion(), new Mushroom(), new RedPeppers()};
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
