package com.msk.decorator;

public abstract class CondimentDecorator extends Beverage{
    protected Beverage beverage;

    public abstract String getDescription();

    @Override
    public void setSize(Size size) {
        super.setSize(size);
    }

    @Override
    public Size getSize() {
        return super.getSize();
    }
}
