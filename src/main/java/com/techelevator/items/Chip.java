package com.techelevator.items;

import java.math.BigDecimal;

public class Chip extends VendingItem {

    public Chip(String name, BigDecimal price, String type) {
        super(name, price, type);
        this.setQuantity(5);
    }

    @Override
    public void dispense() {
        System.out.println("Crunch Crunch, It's Yummy!");
    }

    @Override
    public void decrementQuantity() {
        if (getQuantity() > 0) {
            setQuantity(getQuantity() - 1);
        }
    }
}
