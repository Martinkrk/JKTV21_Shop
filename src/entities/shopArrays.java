/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author pupil
 */
public class shopArrays {
    
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Purchase> purchases = new ArrayList<>();
    private ArrayList<PurchaseProduct> pp = new ArrayList<>();
    private ArrayList<LocalDateTime> dates = new ArrayList<>();

    public shopArrays() {
    }

    public ArrayList<PurchaseProduct> getPp() {
        return pp;
    }

    public void setPp(ArrayList<PurchaseProduct> pp) {
        this.pp = pp;
    }
    
    public void addPp(PurchaseProduct pp) {
        this.pp.add(pp);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    
    public void addProduct(Product product) {
        this.products.add(product);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
    
    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }
    
    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
    }

    public ArrayList<LocalDateTime> getDates() {
        return dates;
    }

    public void setDates(ArrayList<LocalDateTime> dates) {
        this.dates = dates;
    }
    
    public void addDate(LocalDateTime date) {
        this.dates.add(date);
    }
}
