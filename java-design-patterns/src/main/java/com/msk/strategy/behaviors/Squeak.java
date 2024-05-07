package com.msk.strategy.behaviors;

// Concrete implementation of the 'QuackBehavior'.
public class Squeak implements QuackBehavior {

    public void quack() {
        System.out.println("Squeak! Squeak!");
    }
}
