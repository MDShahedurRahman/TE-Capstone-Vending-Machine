package com.techelevator.items;

import java.math.BigDecimal;

public class Drink extends VendingItem {

    public Drink(String name, BigDecimal price, String type) {
        super(name, price, type);
        this.setQuantity(5);
    }

    @Override
    public void dispense() {
        System.out.println("Glug Glug, Chug Chug!");
    }

    @Override
    public void decrementQuantity() {
        if (getQuantity() > 0) {
            setQuantity(getQuantity() - 1);
        }
    }
}
