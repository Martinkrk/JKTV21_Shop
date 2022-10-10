package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Purchase {
    private static int counter = 0;
    private final int Id;
    private Customer Customer;
    private ArrayList<Product> Products = new ArrayList<>();
    private final LocalDateTime PurchaseDate;

    //CONSTRUCTOR
    public Purchase(Customer customer) {
        Id = counter++;
        Customer = customer;
        PurchaseDate = getDate();
    }

    public LocalDateTime getDate(){
        LocalDateTime ldt = LocalDateTime.now();
        return ldt;
    }

    //GET AND SET
    public int getId() {
        return Id;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer customer) {
        Customer = customer;
    }

    public ArrayList<Product> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<Product> products) {
        Products = products;
    }

    public LocalDateTime getPurchaseDate() {
        return PurchaseDate;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "Id=" + Id +
                "," + Customer +
                ", Products=" + getProducts() +
                ", PurchaseDate=" + PurchaseDate +
                '}';
    }
}
