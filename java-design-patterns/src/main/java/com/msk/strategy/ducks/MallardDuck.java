package com.msk.strategy.ducks;

import com.msk.strategy.behaviors.FlyBehavior;
import com.msk.strategy.behaviors.FlyWithWings;
import com.msk.strategy.behaviors.Quack;
import com.msk.strategy.behaviors.QuackBehavior;

public class MallardDuck extends Duck{

    public MallardDuck() {
        super(new FlyWithWings(), new Quack());
    }

    public MallardDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {

    }
}
