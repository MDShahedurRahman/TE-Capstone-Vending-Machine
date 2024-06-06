package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine {
    private Map<String, VendingItem> inventory;
    private BigDecimal balance;

    public Map<String, VendingItem> getInventory() {
        return inventory;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    private static final String LOG_FILE_PATH = "Log.txt";

    public VendingMachine() {
        this.inventory = new TreeMap<>();
        this.balance = BigDecimal.ZERO;
    }

    public void loadInventory(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String slotId = parts[0];
                    String name = parts[1];
                    BigDecimal price = new BigDecimal(parts[2]);
                    String type = parts[3];
                    inventory.put(slotId, new VendingItem(name, price, type));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    public void displayItems() {
        for (Map.Entry<String, VendingItem> entry : inventory.entrySet()) {
            String slotId = entry.getKey();
            VendingItem item = entry.getValue();
            String status = item.getQuantity() > 0 ? String.valueOf(item.getQuantity()) : "SOLD OUT";
            String productItems = String.format("%s  %-20s  %4.2f  %-8s  %s", slotId,  item.getName(),  item.getPrice(),  item.getType(), status);
            System.out.println(productItems);
        }
    }

    public void feedMoney() {

    }

    public void selectProduct() {

    }

    public void finishTransaction() {

    }

    public void generateSalesReport() {

    }
}
