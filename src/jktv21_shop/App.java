package jktv21_shop;

import entities.Customer;
import entities.Product;
import entities.Purchase;
import tools.Tools;

import java.util.ArrayList;

public class App {
    public void run() {
        Tools tool = new Tools();

        boolean mainloop = true;
        Integer input, index;

        System.out.println("Welcome to the Retailer Simulator!");

        do {
            System.out.println("===================");
            System.out.println("Choose an option:");
            System.out.println("0. Exit the program");
            System.out.println("1. Browse...");
            System.out.println("2. Find...");
            System.out.println("3. Add...");

            input = tool.inputInt();
            if(input == null) continue;

            switch (input){
                case 0:
                    mainloop = false;
                    break;

                case 1:
                    loop1:
                    do{
                        System.out.println("===================");
                        System.out.println("Choose number to browse:");
                        System.out.println("1. Products");
                        System.out.println("2. Customers");
                        System.out.println("3. Purchases");
                        System.out.println("4. Back");

                        input = tool.inputInt();
                        if (input == null) continue;

                        switch (input) {
                            case 1:
                                tool.browseProducts();
                                break loop1;
                            case 2:
                                tool.browseCustomers();
                                break loop1;
                            case 3:
                                tool.browsePurchases();
                                break loop1;
                            case 4:
                                break loop1;
                            default:
                                System.err.println("Wrong number! Choose a number from given options!");
                                break;
                        }
                    }while (true);
                    break;

                case 2:
                    loop2:
                    do {
                        System.out.println("===================");
                        System.out.println("Choose number to find:");
                        System.out.println("1. Find a product by its Id");
                        System.out.println("2. Find a customer by its Id");
                        System.out.println("3. Find a purchase by its Id");
                        System.out.println("4. Back");

                        input = tool.inputInt();
                        if (input == null) continue;

                        switch (input) {
                            case 1:
                                tool.findProduct();
                                break loop2;
                            case 2:
                                tool.findCustomer();
                                break loop2;
                            case 3:
                                tool.findPurchase();
                                break loop2;
                            case 4:
                                break loop2;
                            default:
                                System.err.println("Wrong number! Choose a number from given options!");
                                break;
                        }

                    }while (true);
                    break;
                case 3:
                    loop3:
                    do {
                        System.out.println("===================");
                        System.out.println("Choose number to add:");
                        System.out.println("1. Product");
                        System.out.println("2. Purchase");
                        System.out.println("3. Back");

                        input = tool.inputInt();
                        if (input == null) continue;

                        switch (input) {
                            case 1:
                                tool.createProduct();
                                break loop3;
                            case 2:
                                tool.createPurchase();
                                break loop3;
                            case 3:
                                break loop3;
                            default:
                                System.err.println("Wrong number! Choose a number from given options!");
                                break;
                        }
                    }while (true);
                    break;
                default:
                    System.err.println("Wrong number! Choose a number from given options!");
                    break;
            }

        } while (mainloop);

    }
}
