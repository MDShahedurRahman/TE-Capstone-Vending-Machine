package com.techelevator.view;

import com.techelevator.items.Drink;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinkTest {

    private Drink drink;

    @Before
    public void setup() {
        drink = new Drink("Water", new BigDecimal(".50"), "Drink");
    }

    @Test
    public void testCandyInitialization() {
        assertEquals("Water", drink.getName());
        assertEquals(new BigDecimal(".50"), drink.getPrice());
        assertEquals("Drink", drink.getType());
        assertEquals(5, drink.getQuantity());
    }

    @Test
    public void testDecrement_Quantity() {
        drink.decrementQuantity();
        assertEquals(4, drink.getQuantity());
        drink.decrementQuantity();
        assertEquals(3, drink.getQuantity());
    }

    @Test
    public void testDecrement_Quantity_To_Zero() {
        for (int i = 0; i < 5; i++) {
            drink.decrementQuantity();
        }
        assertEquals(0, drink.getQuantity());
        drink.decrementQuantity(); // Trying to decrement below zero should have no effect
        assertEquals(0, drink.getQuantity());
    }

}
