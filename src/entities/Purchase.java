package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        this.purchaseDate = createDate();
    }
    
    public LocalDateTime createDate(){
        LocalDate now = LocalDate.now();
        LocalDate dateNow = now;
        return LocalDateTime.of(dateNow, LocalTime.MIDNIGHT);
    }
    
    public LocalDateTime getDate(){
        return purchaseDate;
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
                " --- Total Price=" + totalPrice +
                " --- Purchase Date=" + purchaseDate + "\n" +
                cust +
                prods;
    }  
}
