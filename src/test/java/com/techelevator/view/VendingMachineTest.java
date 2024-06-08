package com.techelevator.view;

import com.techelevator.VendingMachine;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;


public class VendingMachineTest {
    private VendingMachine vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
        vendingMachine.loadInventory("vendingmachine.csv");
    }

    @Test
    public void test_initialBalance() {
        assertEquals(BigDecimal.ZERO, vendingMachine.getBalance());
    }

    @Test
    public void test_feedMoney_validInput() {
        String input = "5.00";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        vendingMachine.feedMoney();
        assertEquals(new BigDecimal("5.00"), vendingMachine.getBalance());
    }

    @Test
    public void test_feedMoney_negativeInput() {
        String input = "-5.00";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        vendingMachine.feedMoney();
        assertEquals(BigDecimal.ZERO, vendingMachine.getBalance());
    }

    @Test
    public void test_feedMoney_invalidInput() {
        String input = "invalid";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        vendingMachine.feedMoney();
        assertEquals(BigDecimal.ZERO, vendingMachine.getBalance());
    }

    @Test
    public void test_selectProduct_sufficientBalance() {
        String input = "5.00";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        vendingMachine.feedMoney();
        String inputSlotId = "A1";
        InputStream inputStream = new ByteArrayInputStream(inputSlotId.getBytes());
        System.setIn(inputStream);
        vendingMachine.selectProduct();
        assertEquals(new BigDecimal("1.95"), vendingMachine.getBalance());
    }

    @Test
    public void test_selectProduct_insufficientBalance() {
        String input = "1.00";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        vendingMachine.feedMoney();
        String inputSlotId = "A1";
        InputStream inputStream = new ByteArrayInputStream(inputSlotId.getBytes());
        System.setIn(inputStream);
        vendingMachine.selectProduct();
        assertEquals(new BigDecimal("1.00"), vendingMachine.getBalance());
    }

    @Test
    public void test_selectProduct_soldOut() {
        String input = "10.00";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        vendingMachine.feedMoney();
        for(int i = 0; i < 10; i++) {
            String inputSlotId = "C4";
            InputStream inputStream = new ByteArrayInputStream(inputSlotId.getBytes());
            System.setIn(inputStream);
            vendingMachine.selectProduct();
        }
        assertEquals(new BigDecimal("2.50"), vendingMachine.getBalance());
    }

    @Test
    public void test_finish_transaction() {
        String input = "5.00";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        vendingMachine.feedMoney();
        vendingMachine.finishTransaction();
        assertEquals(BigDecimal.ZERO, vendingMachine.getBalance());
    }
}
