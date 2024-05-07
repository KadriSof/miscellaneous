package com.msk.strategy.behaviors;

// Concrete implementation of the 'QuackBehavior'.
public class Quack implements QuackBehavior {

    public void quack() {
        System.out.println("Quack! Quack!");
    }
}
