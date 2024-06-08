package com.techelevator.view;

import com.techelevator.items.Chip;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChipTest {

    private Chip chip;

    @Before
    public void setup() {
        chip = new Chip("Potato", new BigDecimal("2.50"), "Chip");
    }

    @Test
    public void testCandyInitialization() {
        assertEquals("Potato", chip.getName());
        assertEquals(new BigDecimal("2.50"), chip.getPrice());
        assertEquals("Chip", chip.getType());
        assertEquals(5, chip.getQuantity());
    }

    @Test
    public void testDecrement_Quantity() {
        chip.decrementQuantity();
        assertEquals(4, chip.getQuantity());
        chip.decrementQuantity();
        assertEquals(3, chip.getQuantity());
    }

    @Test
    public void testDecrement_Quantity_To_Zero() {
        for (int i = 0; i < 5; i++) {
            chip.decrementQuantity();
        }
        assertEquals(0, chip.getQuantity());
        chip.decrementQuantity(); // Trying to decrement below zero should have no effect
        assertEquals(0, chip.getQuantity());
    }

}
