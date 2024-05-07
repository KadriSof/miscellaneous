package com.msk.strategy;

import com.msk.strategy.behaviors.Quack;
import com.msk.strategy.ducks.*;

/*
Design pattern: Strategy
    The Strategy Pattern defines a family of algorithms, encapsulates
    each one, and makes them interchangeable. Strategy lets the
    algorithm vary independently from clients that use it
 */

public class MiniDuckSimulator {

    public static void main(String[] args) {

        Duck mallard = new MallardDuck();
        mallard.display();
        mallard.performFly();
        mallard.performQuack();

        Duck rubberDuck = new RubberDuck();
        rubberDuck.display();
        rubberDuck.performFly();
        rubberDuck.performQuack();

        rubberDuck.setQuackBehavior(new Quack());
        rubberDuck.performQuack();
    }
}
