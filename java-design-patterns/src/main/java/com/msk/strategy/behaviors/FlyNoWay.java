package com.msk.strategy.behaviors;

// Concrete implementation of the 'FlyBehavior'.
public class FlyNoWay implements FlyBehavior {

    public void fly() {
        System.out.println("Unable to fly!");
    }
}
