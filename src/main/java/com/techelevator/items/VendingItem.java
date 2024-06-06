package com.techelevator.items;

import java.math.BigDecimal;

public abstract class VendingItem implements Vendable{
    private String name;
    private BigDecimal price;
    private String type;
    private int quantity;

    public VendingItem(String name, BigDecimal price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public abstract void decrementQuantity();
}
