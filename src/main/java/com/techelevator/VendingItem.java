package com.techelevator;

import java.math.BigDecimal;

public class VendingItem {
    private String name;
    private BigDecimal price;
    private String type;
    private int quantity;

    public VendingItem(String name, BigDecimal price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = 5;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
}
