package com.msk.decorator.condiments;

import com.msk.decorator.Beverage;
import com.msk.decorator.CondimentDecorator;

public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }


    @Override
    public double cost() {
        double cost = beverage.cost();
        return switch (beverage.getSize()) {
            case TALL -> cost + 0.20;
            case GRANDE -> cost + 0.25;
            case VENTI -> cost + 0.30;
            default -> cost;
        };
    }
}
