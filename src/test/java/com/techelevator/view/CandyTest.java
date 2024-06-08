package com.techelevator.view;

import com.techelevator.items.Candy;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {

    private Candy candy;

    @Before
    public void setup() {
        candy = new Candy("Chocolate Bar", new BigDecimal("1.50"), "Candy");
    }

    @Test
    public void testCandyInitialization() {
        assertEquals("Chocolate Bar", candy.getName());
        assertEquals(new BigDecimal("1.50"), candy.getPrice());
        assertEquals("Candy", candy.getType());
        assertEquals(5, candy.getQuantity());
    }


    @Test
    public void testDecrement_Quantity() {
        candy.decrementQuantity();
        assertEquals(4, candy.getQuantity());
        candy.decrementQuantity();
        assertEquals(3, candy.getQuantity());
    }

    @Test
    public void testDecrement_Quantity_To_Zero() {
        for (int i = 0; i < 5; i++) {
            candy.decrementQuantity();
        }
        assertEquals(0, candy.getQuantity());
        candy.decrementQuantity(); // Trying to decrement below zero should have no effect
        assertEquals(0, candy.getQuantity());
    }
}
