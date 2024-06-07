package com.techelevator;

import com.techelevator.items.VendingItem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Logger {
    private final String logFilePath;

    public Logger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void log(String message) {
        try (PrintWriter logWriter = new PrintWriter(new FileWriter(logFilePath, true))) {
            logWriter.println(getLogTimestamp() + " " + message);
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    public void generateSalesReport(Map<String, VendingItem> inventory, BigDecimal totalSales) {
        String filename = "SalesReport_" + getReportTimestamp() + ".txt";
        try (PrintWriter reportWriter = new PrintWriter(new FileWriter(filename))) {
            for (Map.Entry<String, VendingItem> entry : inventory.entrySet()) {
                VendingItem item = entry.getValue();
                int itemsSold = 5 - item.getQuantity();
                reportWriter.println(item.getName() + "|" + itemsSold);
            }
            reportWriter.println();
            reportWriter.println("**TOTAL SALES** $" + totalSales);
        } catch (IOException e) {
            System.out.println("Error writing sales report: " + e.getMessage());
        }
    }

    private String getLogTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return dateFormat.format(new Date());
    }
    private String getReportTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(new Date());
    }
}