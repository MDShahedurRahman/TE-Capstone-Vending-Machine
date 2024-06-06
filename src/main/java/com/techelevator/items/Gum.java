package com.techelevator.items;

import java.math.BigDecimal;

public class Gum extends VendingItem {

    public Gum(String name, BigDecimal price, String type) {
        super(name, price, type);
        this.setQuantity(5);
    }

    @Override
    public void dispense() {
        System.out.println("Chew Chew, Pop!");
    }

    @Override
    public void decrementQuantity() {
        if (getQuantity() > 0) {
            setQuantity(getQuantity() - 1);
        }
    }
}
