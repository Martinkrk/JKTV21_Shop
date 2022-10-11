package tools;

import entities.Customer;
import entities.Product;
import entities.Purchase;
import entities.Arrays;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.Scanner;

public class Tools {
        Scanner scn = new Scanner(System.in);
        Arrays ars = new Arrays();
                
    //Did user input an int
    public Integer inputInt(){
        int input;
        try{
            input = scn.nextInt();
        }
        catch (java.util.InputMismatchException e){
            System.err.println("Wrong input! Use integers!");
            return null;
        }
        catch (Exception e){
            System.err.println(e);
            return null;
        }
        finally {
            scn.nextLine();
        }
        return input;
    }

    public Double inputDouble(){
        Double input;
        try{
            input = scn.nextDouble();
        }
        catch (java.util.InputMismatchException e){
            System.err.println("Wrong input! Use doubles!");
            return null;
        }
        catch (Exception e){
            System.err.println(e);
            return null;
        }
        finally {
            scn.nextLine();
        }
        return input;
    }

    public void createProduct(){
        String name;
        Double price;
        System.out.println("Enter products name:");
        name = scn.nextLine();
        do{
            System.out.println("Enter products price:");
            price = inputDouble();

            if(price != null) break;
        }while (true);

        ars.setProducts(new Product(name, price));
    }

    public void browseProducts(){
        for(Object product : ars.getProducts()){
            System.out.println(product);
        }
    }
    public void browseCustomers(){
        for(Object customer : ars.getCustomers()){
            System.out.println(customer);
        }
    }
    public void browsePurchases(){
        for(Object purchase : ars.getPurchases()){
            System.out.println(purchase);
        }
    }

    public void createPurchase() {
        
        Integer customerId;
        Customer customer;
        //CHOOSE CUSTOMER's ID
        do {
            do {
                System.out.println("Choose an id of a customer. 0 if new customer");
                customerId = inputInt();
                if (customerId != null && customerId <= ars.getCustomers().size()) break;
            } while (true);

            customer = assertClient(customerId);
        
            if(customer != null) break;
        }while (true);

        ArrayList<Product> products;
        products = addProducts();
        
        double total = 0;
        for(Product prod : products){
            total += prod.getCost();
        }
        //IF NEW CLIENT, THEN APPLY A DISCOUNT
        if(customer.getId() != 0 && customer.isUsedDiscount() == false){
            customer.setUsedDiscount(true);
            //APPLY A DISCOUNT
            total = applyDiscount(total);
        }   
        Purchase purchase = new Purchase(customer); 
        purchase.setProducts(products);
        purchase.setTotalPrice(total);
        ars.setPurchases(purchase);
    }
    
    public ArrayList<Product> addProducts(){
        ArrayList<Product> products = new ArrayList<>();    

        boolean outerDo = true;
        do {
            do {
                System.out.println("Choose an id of a product.");
                Integer productId = inputInt();
                if (productId != null && productId <= ars.getProducts().size() - 1) {
                    products.add(ars.getProducts().get(productId));
                    break;
                }
            } while (true);

            do {
                System.out.println("[add] new good or [finish]?");
                String addorFinish = scn.nextLine();
                if (addorFinish.contains("add")) {
                    break;
                } else if (addorFinish.contains("finish")) {
                    outerDo = false;
                    break;
                } else {
                    System.err.println("Wrong command!");
                }
            } while (true);
        } while (outerDo);
        return products;
    }
    
    public Customer assertClient(Integer customerId){
        //CHOOSE CUSTOMER's ID
        Customer customer;
        String yaynay;

        //NEW CLIENT?
        if(customerId == 0){
        do{
            System.out.println("Register new client? Y/N");
            yaynay = scn.nextLine();
            if(yaynay.toUpperCase().contains("Y")){
                customer = createCustomer(customerId);
                break;
            } else if (yaynay.toUpperCase().contains("N")) {
                customer = ars.getCustomers().get(0);
                break;
            }
            else{
                System.out.println("Incorrect input! Try to type Y or N");
            }
        }while (true);
            ars.setCustomers(customer);
        }
        else {
            if(customerId <= ars.getCustomers().size()){
                customer = ars.getCustomers().get(customerId);    
            }
            else{
                return null;
            }
        }
        return customer;
    }
        
    public double applyDiscount(double total){
        total = total *0.9;
        
        BigDecimal bd = BigDecimal.valueOf(total);
        bd = bd.setScale(2, RoundingMode.CEILING);
        
        System.out.println("Applied an one time discount!");

        return bd.doubleValue();
    }

    public Customer createCustomer(int id){
        Customer customer = new Customer(true);
        return customer;
    }
    //FIND
    public void find(ArrayList type){
        Integer Id;
        do {
            System.out.println("Enter item's Id: ");
            Id = inputInt();

            if (Id != null) {
                if (Id < type.size()) {
                    break;
                } else {
                    System.err.println("The number is out of range!");
                }
            }
        } while (true);

        System.out.println(type.get(Id));
    }

    public void findProduct() {
        find(ars.getProducts());
    }
    public void findCustomer() {
        find(ars.getCustomers());
    }
    public void findPurchase() {
        find(ars.getPurchases());
    }
}
