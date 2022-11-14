package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalPrice;
    private LocalDateTime purchaseDate;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Customer customer;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity = Product.class)
    private ArrayList<Product> products;

    public Purchase() {
    }

    public Purchase(Customer customer, ArrayList<Product> products, double totalPrice) {
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

//    public String getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(String customer) {
//        this.customer = customer;
//    }
//
//    public ArrayList<String> getProducts() {
//        return products;
//    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    
    public void addProduct(Product product) {
        this.products.add(product);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

//    @Override
//    public String toString() {
//        String cust = "\t" + customer + "\n";
//        String prods = "";
//        for(String prod : products){
//            prods += "\t" + prod;
//        }
//        return "Purchase" + 
//                " : Id=" + Id + 
//                " --- Total Price=" + totalPrice +
//                " --- Purchase Date=" + purchaseDate + "\n" +
//                cust +
//                prods;
//    }  
}
