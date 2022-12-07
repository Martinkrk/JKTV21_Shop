package tools;

import entities.Customer;
import entities.Product;
import entities.Purchase;
import entities.PurchaseProduct;
import entities.Statistics;
import entities.shopArrays;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Tools {
    Scanner scn = new Scanner(System.in);
    shopArrays ars = new shopArrays();
    Statistics stats = new Statistics();
    DataBaseManager dbm = new DataBaseManager();

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
        dbm.saveProduct(ars.getProducts().get(ars.getProducts().size()-1));
        ars.getProducts().clear();
        for(Product p : dbm.DBExtractProducts()){
            ars.addProduct(p);
        }
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

        
        //Collect all instances and count them
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
        //Check if any product is in stock
        for(Product prod : countedMap.keySet()){
            //If not enough in stock, then close method
            if(countedMap.get(prod) > prod.getAmount()){
                System.err.println("Not enough products in stock!");
                return;
            }
        }
        
        double total = 0;
        //If in stock, then substract from stock
        for(Product prod : countedMap.keySet()){
            prod.substractStock(countedMap.get(prod));
            total += prod.getCost();
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
                dbm.changeCustomer(customer);
            }
            else{
                canAfford = false;
                System.err.println("The customer may not acquire these items for they has insufficient funds!");
            }
        }
        if(canAfford){
            
            Purchase purchase = new Purchase();
            purchase.addCustomer(customer);
            for(Product prod : countedMap.keySet()){
                PurchaseProduct pp = new PurchaseProduct(purchase, prod, countedMap.get(prod));
                dbm.savePurchaseProduct(pp);
                stats.addTotalProductsSold();
            }
            purchase.setTotalPrice(total);

            //ADD TO SHOP's EARNINGS
            stats.addTotalEarnings(total);

            dbm.savePurchase(purchase);
            ars.addPurchase(purchase);
        }
    }
    
    public ArrayList<Product> addProducts(){
        ArrayList<Product> products = new ArrayList<>();    

        boolean outerDo = true;
        do {
            do {
                System.out.println("Choose an id of a product.");
                Integer productId = inputInt(new int[] {1, ars.getProducts().size()}) - 1;
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
        dbm.saveCustomer(customer);
        return customer;
    }
    
    public void addBalanceToCustomer(){
        Integer id;
        Double money;
        
        System.out.println("Input Customer's ID:");
        id = inputInt(new int[] {1, ars.getCustomers().size()});
        
        System.out.println("Input the amount of cash to add:");
        money = inputDouble(new double[] {0.01, 10000});
        
        ars.getCustomers().get(id).addBalance(money);
        dbm.changeCustomer(ars.getCustomers().get(id));
    }
    
    public void addStock(){
        int input, amount;
        
        System.out.println("Choose a product to replenish:");
        input = inputInt(new int[] {1, ars.getProducts().size()});
        
        System.out.println("Choose an amount of products to add:");
        amount = inputInt(new int[] {1, 10000});
        
        ars.getProducts().get(input-1).addStock(amount);
        dbm.changeProduct(ars.getProducts().get(input-1));
        
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

    public void onOpenDB(){
        
        for(Product p : dbm.DBExtractProducts()){
            ars.addProduct(p);
        }
        for(Customer c : dbm.DBExtractCustomers()){
            ars.addCustomer(c);
        }
        for(Purchase u : dbm.DBExtractPurchases()){
            ars.addPurchase(u);
        }
        for(PurchaseProduct pp : dbm.DBExtractPurchaseProduct()){
            ars.addPp(pp);
        }
        
    }
    
    public ArrayList<Product> getProductsForPurchase(Purchase purchase) {
        for(PurchaseProduct pp : dbm.DBExtractPurchaseProduct()){
            ars.addPp(pp);
        }
        ArrayList<Product> prods = new ArrayList<>();
        for(PurchaseProduct pp : ars.getPp()) {
            if(pp.getPurchase().getId() == purchase.getId()) {
                prods.add(pp.getProduct());
            }
        }
        return prods;
    }
}