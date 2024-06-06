package com.techelevator;

import com.techelevator.view.VendingMenu;



public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private VendingMenu menu;
	private VendingMachine vendingMachine;

	public VendingMachineCLI(VendingMenu menu, VendingMachine vendingMachine) {
		this.menu = menu;
		this.vendingMachine = vendingMachine;
	}

	public void run() {
		boolean running = true;
		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			switch (choice) {
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					vendingMachine.displayItems();
					break;
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseMenu();
					break;
				case MAIN_MENU_OPTION_EXIT:
					running = false;
					break;
				case MAIN_MENU_SECRET_OPTION:
					vendingMachine.generateSalesReport();
					break;
			}
		}
	}

	private void purchaseMenu() {
		boolean purchasing = true;
		while (purchasing) {
			menu.displayMessage("Current Money Provided: $" + vendingMachine.getBalance());
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			switch (choice) {
				case PURCHASE_MENU_OPTION_FEED_MONEY:
					vendingMachine.feedMoney();
					break;
				case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
					vendingMachine.selectProduct();
					break;
				case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
					vendingMachine.finishTransaction();
					purchasing = false;
					break;
			}
		}
	}

	public static void main(String[] args) {
		VendingMenu menu = new VendingMenu(System.in, System.out);
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.loadInventory("vendingmachine.csv");
		VendingMachineCLI cli = new VendingMachineCLI(menu, vendingMachine);
		cli.run();
	}
}
