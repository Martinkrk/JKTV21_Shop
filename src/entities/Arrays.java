package entities;

import java.util.ArrayList;

public class Arrays {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Purchase> purchases = new ArrayList<>();

    public Arrays() {
        customers.add(new Customer(false));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customer) {
        this.customers.add(customer);
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchase purchase) {
        this.purchases.add(purchase);
    }
}
