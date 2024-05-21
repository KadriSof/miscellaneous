package com.msk.decorator;

import com.msk.decorator.beverages.DarkRoast;
import com.msk.decorator.beverages.Espresso;
import com.msk.decorator.beverages.HouseBlend;
import com.msk.decorator.condiments.Mocha;
import com.msk.decorator.condiments.Soy;
import com.msk.decorator.condiments.Whip;

public class StarbuzzCoffee {

    public static void main(String[] args) {

        // Order up an espresso, no condiments, and print its description and cost.
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        // Make a Dark Roast object.
        Beverage beverage2 = new DarkRoast();
        // Wrap it in a Mocha.
        beverage2 = new Mocha(beverage2);
        // Wrap it in a second Mocha (Double Mocha!).
        beverage2 = new Mocha(beverage2);
        // Wrap it in a whip.
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

        // And a House Blend with Soy, Mocha, and Whip.
        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
    }
}
