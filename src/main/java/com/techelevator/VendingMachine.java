package com.techelevator;

import com.techelevator.items.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
                    switch (type) {
                        case "Chip":
                            inventory.put(slotId, new Chip(name, price, type));
                            break;
                        case "Candy":
                            inventory.put(slotId, new Candy(name, price, type));
                            break;
                        case "Drink":
                            inventory.put(slotId, new Drink(name, price, type));
                            break;
                        case "Gum":
                            inventory.put(slotId, new Gum(name, price, type));
                            break;
                        default:
                            // Handle unknown type
                            System.out.println("Unknown type: " + type);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    public void displayItems() {
        System.out.println(String.format("%-8s  %-20s  %-7s  %-6s  %s", "SlotID", "Name", "Price", "Type", "Quantity"));
        for (Map.Entry<String, VendingItem> entry : inventory.entrySet()) {
            String slotId = entry.getKey();
            VendingItem item = entry.getValue();
            String status = item.getQuantity() > 0 ? String.valueOf(item.getQuantity()) : "SOLD OUT";
            String productItems = String.format("%-8s  %-20s  $%-6.2f  %-7s  %s", slotId,  item.getName(),  item.getPrice(),  item.getType(), status);
            System.out.println(productItems);
        }
    }

    public void feedMoney() {
        Scanner userInput = new Scanner(System.in);
        System.out.print("How much money are you depositing? ");
        try {
            BigDecimal amount = new BigDecimal(userInput.nextLine());
            if (amount.compareTo(BigDecimal.ZERO) > 0) {
                // Adds user input to balance
                balance = balance.add(amount);
//                System.out.println("Current balance: " + balance);
            } else {
                System.out.println("Please enter a positive amount.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
        }

    }

    public void selectProduct() {
        displayItems();
        Scanner input = new Scanner(System.in);
        System.out.print("Please input item code: ");
        String userInputCode = input.nextLine();
        if (inventory.containsKey(userInputCode)) {
            if (inventory.get(userInputCode).getQuantity() > 0) {
                if (balance.compareTo(inventory.get(userInputCode).getPrice()) >= 0) {
                    // Subtracts price from balance
                    balance = balance.subtract(inventory.get(userInputCode).getPrice());
                    // Decrements quantity by 1
                    inventory.get(userInputCode).decrementQuantity();
                    // Need to make sound effect based on item type
                    inventory.get(userInputCode).dispense();
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Item is sold out.");
            }
        } else {
            System.out.println("Invalid Input.");
        }

    }

    public void finishTransaction() {
        BigDecimal dollars = balance.setScale(0, RoundingMode.DOWN);  // Whole dollars
        BigDecimal cents = balance.remainder(BigDecimal.ONE).multiply(new BigDecimal("100")).setScale(0, RoundingMode.DOWN);  // Cents as whole number

        BigDecimal quarters = cents.divide(new BigDecimal("25"), 0, RoundingMode.DOWN);  // Quarters
        cents = cents.subtract(quarters.multiply(new BigDecimal("25")));  // Remaining cents

        BigDecimal dimes = cents.divide(new BigDecimal("10"), 0, RoundingMode.DOWN);  // Dimes
        cents = cents.subtract(dimes.multiply(new BigDecimal("10")));  // Remaining cents

        BigDecimal nickels = cents.divide(new BigDecimal("5"), 0, RoundingMode.DOWN);  // Nickels
        cents = cents.subtract(nickels.multiply(new BigDecimal("5")));  // Remaining cents

        BigDecimal pennies = cents;  // Pennies

        System.out.println("_____Change_____");
        System.out.println("Dollars: " + dollars);
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        System.out.println("Pennies: " + pennies);
        balance = BigDecimal.ZERO;  // Reset balance
    }


    public void generateSalesReport() {

    }





}



