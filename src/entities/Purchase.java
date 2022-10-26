package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Purchase implements Serializable {
    private static int counter = 0;
    private final int Id;
    private String customer;
    private ArrayList<String> products = new ArrayList<>();
    private double totalPrice;
    private final LocalDateTime purchaseDate;

    public Purchase(String customer) {
        this.Id = counter++;
        this.customer = customer;
        this.products = products;
        this.totalPrice = totalPrice;
        this.purchaseDate = getDate();
    }
    
    public LocalDateTime getDate(){
        LocalDateTime ldt = LocalDateTime.now();
        return ldt;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Purchase.counter = counter;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }
    
    public void addProduct(String product) {
        this.products.add(product);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        String cust = "\t" + customer + "\n";
        String prods = "";
        for(String prod : products){
            prods += "\t" + prod;
        }
        return "Purchase" + 
                " : Id=" + Id + 
                " --- totalPrice=" + totalPrice + "\n" +
                cust +
                prods;
    }  
}
