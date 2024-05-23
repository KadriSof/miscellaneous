package com.msk.decorator.condiments;

import com.msk.decorator.Beverage;
import com.msk.decorator.CondimentDecorator;

public class SteamedMilk extends CondimentDecorator {

    public SteamedMilk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Steamed Milk";
    }

    @Override
    public double cost() {
        double cost = beverage.cost();
        return switch (beverage.getSize()) {
            case TALL -> cost + 0.10;
            case GRANDE -> cost + 0.15;
            case VENTI -> cost + 0.20;
            default -> cost;
        };
    }
}
