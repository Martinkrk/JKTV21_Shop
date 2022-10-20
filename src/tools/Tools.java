package tools;

import entities.Customer;
import entities.Product;
import entities.Purchase;
import entities.Arrays;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.nio.file.Files;

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

    public String[] browseProducts(){
        String[] str = new String[ars.getProducts().size()];
        for(int i = 0; i < ars.getProducts().size(); i++){
            str[i] = ars.getProducts().get(i).toString();
        }
        return str;
    }
    public String[] browseCustomers(){
        String[] str = new String[ars.getCustomers().size()];
        for(int i = 0; i < ars.getCustomers().size(); i++){
            str[i] = ars.getCustomers().get(i).toString();
        }
        return str;
    }
    public String[] browsePurchases(){
        String[] str = new String[ars.getPurchases().size()];
        for(int i = 0; i < ars.getPurchases().size(); i++){
            str[i] = ars.getPurchases().get(i).toString();
        }
        return str;
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
                customer = createCustomer();
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

    public Customer createCustomer(){
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
    
    //WHEN USER OPENS APPLICATION
    //READ FROM FILES
    public void onOpen(){

        //FILES
            if(!new File("data").exists()){
                new File("data").mkdirs();
            }

        File prods = new File("data\\products.txt");
        File custs = new File("data\\customers.txt");
        File purcs = new File("data\\purchases.txt");
            
        try{
            if(prods.createNewFile()) System.out.println("File created");
            if(custs.createNewFile()) System.out.println("File created"); 
            if(purcs.createNewFile()) System.out.println("File created");
        }
        catch(Exception e){
            System.err.println(e);
        }
        
        if(prods.canRead() && prods.length() != 0){
            extractProducts();
        }
          
        if(custs.canRead() && custs.length() != 0){
            extractCustomers();
        }
        else{
            ars.setCustomers(new Customer(false));
        }

        if(purcs.canRead() && purcs.length() != 0){
            extractPurchases();
        }
    }
    
    public void extractProducts(){
        ArrayList<Product> arr = new ArrayList<>();
        try{
                FileInputStream file = new FileInputStream("data\\products.txt");
                ObjectInputStream input = new ObjectInputStream(file);
                
                arr = (ArrayList<Product>) input.readObject();
                input.close();
        }
        catch(Exception e){
            System.err.println(e);
        }
        
        ars.defineProducts(arr);
        Product.setCounter(arr.size());
    }
    
    public void extractCustomers(){
        ArrayList<Customer> arr = new ArrayList<>();
        try{
            FileInputStream file = new FileInputStream("data\\customers.txt");
            ObjectInputStream input = new ObjectInputStream(file);

            arr = (ArrayList<Customer>) input.readObject();
            input.close();
        }
        catch(Exception e){
            System.err.println(e);
        }

        ars.defineCustomers(arr);
        Customer.setCounter(arr.size());
    }

    public void extractPurchases(){
        ArrayList<Purchase> arr = new ArrayList<>();
        try{
                FileInputStream file = new FileInputStream("data\\purchases.txt");
                ObjectInputStream input = new ObjectInputStream(file);
                
                arr = (ArrayList<Purchase>) input.readObject();
                input.close();
        }
        catch(Exception e){
            System.err.println(e);
        }
        ars.definePurchases(arr);
        Purchase.setCounter(arr.size());
    }
    
      
    //WHEN USER CLOSES APPLICATION
    //WRITE TO FILES
    public void onClose(){
        //IF THERE ARE ELEMENTS IN PRODUCTS
        if(ars.getProducts().size() != 0){
            //PRODUCTS
            try{
                FileOutputStream fileProducts = new FileOutputStream("data\\products.txt");
                ObjectOutputStream outputProducts = new ObjectOutputStream(fileProducts);

                outputProducts.writeObject(ars.getProducts());
                outputProducts.close();
            }
            catch(Exception e){
                System.err.println(e);
            }  
        }
        
        //IF THERE ARE ELEMENTS IN CUSTOMERS
        if(ars.getCustomers().size() != 0){
            //CUSTOMERS
            try{
                FileOutputStream fileCustomers = new FileOutputStream("data\\customers.txt");
                ObjectOutputStream outputCustomers = new ObjectOutputStream(fileCustomers);

                outputCustomers.writeObject(ars.getCustomers());
                outputCustomers.close();
            }
            catch(Exception e){
                System.err.println(e);
            }  
        }
        
        //IF THERE ARE ELEMENTS IN PURCHASES
        if(ars.getPurchases().size() != 0){
            //PURCHASES
            try{
                FileOutputStream filePurchases = new FileOutputStream("data\\purchases.txt");
                ObjectOutputStream outputPurchases = new ObjectOutputStream(filePurchases);

                outputPurchases.writeObject(ars.getPurchases());
                outputPurchases.close();
            }
            catch(Exception e){
                System.err.println(e);
            }  
        }
    }
}
