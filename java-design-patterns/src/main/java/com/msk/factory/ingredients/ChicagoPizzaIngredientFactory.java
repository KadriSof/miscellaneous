package com.msk.factory.ingredients;

import com.msk.factory.ingredients.regional.FrozenClams;
import com.msk.factory.ingredients.regional.MozzarellaCheese;
import com.msk.factory.ingredients.regional.PlumTomatoSauce;
import com.msk.factory.ingredients.regional.ThickCrustDough;
import com.msk.factory.ingredients.primary.*;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[0];
    }

    @Override
    public Pepperoni createPepperoni() {
        return null;
    }

    @Override
    public Clams createClams() {
        return new FrozenClams();
    }
}
