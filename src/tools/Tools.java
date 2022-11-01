package tools;

import entities.Customer;
import entities.Product;
import entities.Purchase;
import entities.Statistics;
import entities.shopArrays;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.nio.file.Files;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Tools {
    Scanner scn = new Scanner(System.in);
    shopArrays ars = new shopArrays();
    Statistics stats = new Statistics();

//Did user input an int
    public Integer inputInt(int[] range){
        int input;
        do{
            try{
                input = scn.nextInt();
            }
            catch (java.util.InputMismatchException e){
                System.err.println("Wrong input! Use integers!");
                continue;
            }
            catch (Exception e){
                System.err.println(e);
                continue;
            }
            finally {
                scn.nextLine();
            }
                if(input < range[0] || input > range[1]){
                    System.out.println("Wrong range!");
                    continue;
                }
                else{
                    break;
                }
        }while(true);
        
        return input;
    }

    public Double inputDouble(double[] range){
        Double input;
        do{
            try{
                input = scn.nextDouble();
            }
            catch (java.util.InputMismatchException e){
                System.err.println("Wrong input! Use Doubles!");
                continue;
            }
            catch (Exception e){
                System.err.println(e);
                continue;
            }
            finally {
                scn.nextLine();
            }
                if(input < range[0] || input > range[1]){
                    System.out.println("Wrong range!");
                    continue;
                }
                else{
                    break;
                }
        }while(true);
        
        return input;
    }

    public void createProduct(){
        String name;
        Double price;
        Integer amount;
        System.out.println("Enter products name:");
        name = scn.nextLine();
        do{
            System.out.println("Enter products price:");
            price = inputDouble(new double[] {0.01, 10000}); //param range

            if(price != null) break;
        }while (true);
        
        do{
            System.out.println("Enter amount of products to stock:");
            amount = inputInt(new int[] {1,10000}); //param range

            if(amount != null) break;
        }while (true);

        ars.addProduct(new Product(name, price, amount));
    }

    public void browseProducts(){
        if(ars.getProducts().size() == 0){
            System.out.println("No Data");
        }
        else{
            for(Product p : ars.getProducts()){
                System.out.println(p);
            }     
        }
    }
    public void browseCustomers(){
        if(ars.getCustomers().size() == 0){
            System.out.println("No Data");
        }
        else{
            for(Customer c : ars.getCustomers()){
                System.out.println(c);
            }
        }
    }
    public void browsePurchases(){
        if(ars.getPurchases().size() == 0){
            System.out.println("No Data");
        }
        else{
            for(Purchase p : ars.getPurchases()){
                System.out.println(p);
            }
        }
    }

    public void createPurchase() {
        Integer customerId;
        Customer customer;
        boolean canAfford = true;
        //CHOOSE CUSTOMER's ID
        do {
            do {
                System.out.println("Choose an id of a customer. 0 if new customer");
                customerId = inputInt(new int[] {0, ars.getCustomers().size()-1});
                if (customerId != null && customerId <= ars.getCustomers().size()) break;
            } while (true);

            customer = assertClient(customerId);
        
            if(customer != null) break;
        }while (true);

        ArrayList<Product> products;
        products = addProducts();
        HashMap<Product, Integer> countedMap = new HashMap<>();
        for(Product prod : products){
            if(countedMap.containsKey(prod)){
                countedMap.replace(prod, countedMap.get(prod), countedMap.get(prod)+1);
            }
            else{
                countedMap.put(prod, 1);
            }
        }
        System.out.println(countedMap);
        
        double total = 0;
        boolean instock = true;
        for(Product prod : countedMap.keySet()){
            if(countedMap.get(prod) > prod.getAmount()){
                instock = false;
                break;
            }
        }
        if(instock == false){
            System.err.println("Not enough products in stock!");
            return;
        }
        
        for(Product prod : products){
            total += prod.getCost();
            prod.substractStock();
        }
        //IF NEW CLIENT, THEN APPLY A DISCOUNT
        if(customer.getId() != 0){
            if(customer.isUsedDiscount() == false){
                customer.setUsedDiscount(true);
                //APPLY A DISCOUNT
                total = applyDiscount(total);
            }   
            
            //SUBSTRACT FROM CUSTROMERS's BALANCE
            if(customer.getBalance() > total){
                customer.subtractBalance(total);
            }
            else{
                canAfford = false;
                System.err.println("The customer may not acquire these items for they has insufficient funds!");
            }
        }
        if(canAfford){
            
            Purchase purchase = new Purchase(customer.toString()); 
            for(Product p : products){
                purchase.addProduct(p.toString());
                stats.addTotalProductsSold();
            }
            purchase.setTotalPrice(total);

            //ADD TO SHOP's EARNINGS
            stats.addTotalEarnings(total);

            ars.addPurchase(purchase);
        }
    }
    
    public ArrayList<Product> addProducts(){
        ArrayList<Product> products = new ArrayList<>();    

        boolean outerDo = true;
        do {
            do {
                System.out.println("Choose an id of a product.");
                Integer productId = inputInt(new int[] {0, ars.getProducts().size()-1});
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
                System.out.println("Incorrect input! Try Y or N");
            }
        }while (true);
            ars.addCustomer(customer);
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
        Customer customer = new Customer(true, false, 100.00);
        stats.addTotalRegisteredCustomers();
        return customer;
    }
    
    public void createNewCustomer(){
        Customer customer = new Customer(true, false, 100.00);
        stats.addTotalRegisteredCustomers();
        ars.addCustomer(customer);
    }
    
    public void addBalanceToCustomer(){
        Integer id;
        Double money;
        
        System.out.println("Input Customer's ID:");
        id = inputInt(new int[] {1, ars.getCustomers().size()});
        
        System.out.println("Input the amount of cash to add:");
        money = inputDouble(new double[] {0.01, 10000});
        
        ars.getCustomers().get(id).addBalance(money);
    }
    
    public void addStock(){
        int input, amount;
        
        System.out.println("Choose a product to replenish:");
        input = inputInt(new int[] {0, ars.getProducts().size()});
        
        System.out.println("Choose an amount of products to add:");
        amount = inputInt(new int[] {1, 10000});
        
        ars.getProducts().get(input).addStock(amount);
        
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
            ars.addCustomer(new Customer(false, false));
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
        
        ars.setProducts(arr);
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

        ars.setCustomers(arr);
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
        ars.setPurchases(arr);
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
        
        //SAVE STATS
        try{
            FileOutputStream fileStats = new FileOutputStream("data\\statistics.txt");
            ObjectOutputStream outputStats = new ObjectOutputStream(fileStats);

            outputStats.writeObject(stats);
            outputStats.close();
            }
            catch(Exception e){
                System.err.println(e);
            }
    }
    
    //STATISTICS FOR DIFFERENT TIME RANGES
    
    public void createDateRanges(){
        LocalDate now = LocalDate.now();
        ars.getDates().clear();
        
        LocalDate yearStart = now.withDayOfYear(1).minusDays(1);
        LocalDateTime yearStartD = LocalDateTime.of(yearStart, LocalTime.MIDNIGHT);
        ars.addDate(yearStartD);
        
        LocalDate monthStart = now.withDayOfMonth(1).minusDays(1);
        LocalDateTime monthStartD = LocalDateTime.of(monthStart, LocalTime.MIDNIGHT);
        ars.addDate(monthStartD);
        System.out.println(monthStartD);
        
        LocalDate weekStart = now.with(DayOfWeek.MONDAY);
        LocalDateTime weekStartD = LocalDateTime.of(weekStart, LocalTime.MIDNIGHT);
        ars.addDate(weekStartD);
        
        LocalDate thisDay = LocalDate.now().minusDays(1);
        LocalDateTime thisDayD = LocalDateTime.of(thisDay, LocalTime.MIDNIGHT);
        ars.addDate(thisDayD);
//        System.out.println(thisDayD);
    }
    
        public void showStatistics(){
        String out = "";
        int[] costs = new int[4];
        
        for(int i = 0; i < ars.getDates().size(); i++){
            for(Purchase p : ars.getPurchases()){
                if(p.getDate().isAfter(ars.getDates().get(i))){
                    costs[i] += p.getTotalPrice();
                }
            }
        }
        
        out += "===THIS YEAR===\n"
                + "Total earned: " + costs[0] + "\n\n" +
                "===THIS MONTH===\n" 
                + "Total earned: " + costs[1] + "\n\n" +
                "===THIS WEEK===\n"
                + "Total earned: " + costs[2] + "\n\n" +
                "===  TODAY  ===\n"
                + "Total earned: " + costs[3] + "\n";
        
            System.out.println(out);
    }
}