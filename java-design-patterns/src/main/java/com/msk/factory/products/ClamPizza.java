package com.msk.factory.products;

import com.msk.factory.ingredients.PizzaIngredientFactory;

public class ClamPizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + this.name);
        dough = this.ingredientFactory.createDough();
        sauce = this.ingredientFactory.createSauce();
        cheese = this.ingredientFactory.createCheese();
        clam = this.ingredientFactory.createClams();
    }
}
