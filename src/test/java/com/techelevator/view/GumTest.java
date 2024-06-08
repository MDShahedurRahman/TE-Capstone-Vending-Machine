package com.techelevator.view;

import com.techelevator.items.Gum;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {

    private Gum gum;

    @Before
    public void setup() {
        gum = new Gum("Minty Gum", new BigDecimal("1.50"), "Gum");
    }

    @Test
    public void testCandyInitialization() {
        assertEquals("Minty Gum", gum.getName());
        assertEquals(new BigDecimal("1.50"), gum.getPrice());
        assertEquals("Gum", gum.getType());
        assertEquals(5, gum.getQuantity());
    }

    @Test
    public void testDecrement_Quantity() {
        gum.decrementQuantity();
        assertEquals(4, gum.getQuantity());
        gum.decrementQuantity();
        assertEquals(3, gum.getQuantity());
    }

    @Test
    public void testDecrement_Quantity_To_Zero() {
        for (int i = 0; i < 5; i++) {
            gum.decrementQuantity();
        }
        assertEquals(0, gum.getQuantity());
        gum.decrementQuantity(); // Trying to decrement below zero should have no effect
        assertEquals(0, gum.getQuantity());
    }

}
