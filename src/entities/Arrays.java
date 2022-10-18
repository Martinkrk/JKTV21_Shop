package entities;

import java.util.ArrayList;

public class Arrays {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Purchase> purchases = new ArrayList<>();

    public Arrays() {
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }
    
    public void defineProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customer) {
        this.customers.add(customer);
    }
    
    public void defineCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchase purchase) {
        this.purchases.add(purchase);
    }
    
    public void definePurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }
}
