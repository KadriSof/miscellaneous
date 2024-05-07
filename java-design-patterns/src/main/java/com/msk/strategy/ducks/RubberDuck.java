package com.msk.strategy.ducks;

import com.msk.strategy.behaviors.FlyBehavior;
import com.msk.strategy.behaviors.FlyNoWay;
import com.msk.strategy.behaviors.QuackBehavior;
import com.msk.strategy.behaviors.Squeak;

public class RubberDuck extends Duck{

    public RubberDuck() {
        super(new FlyNoWay(), new Squeak());
    }

    public RubberDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {

    }
}
