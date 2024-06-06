package com.techelevator.items;

import java.math.BigDecimal;

public class Candy extends VendingItem {

    public Candy(String name, BigDecimal price, String type) {
        super(name, price, type);
        this.setQuantity(5);
    }

    @Override
    public void dispense() {
        System.out.println("Munch Munch, Mmm Mmm Good!");
    }

    @Override
    public void decrementQuantity() {
        if (getQuantity() > 0) {
            setQuantity(getQuantity() - 1);
        }
    }
}
