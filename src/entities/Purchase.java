package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import tools.Tools;

@Entity
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalPrice;
    private LocalDateTime purchaseDate;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Customer customer;
    
    public Purchase() {
        
    }
    
    public Purchase(Customer customer, double totalPrice) {
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
//    public List<String> getProducts() {
//        return products;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    
    public void addCustomer(Customer customer){
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        Tools tool = new Tools();
        String cust = "\t" + customer + "\n";
        String prods = "";
        for(Product prod : tool.getProductsForPurchase(this)){
            prods += "\t" + prod;
        }
        return "Purchase" + 
                " : Id=" + id + 
                " --- Total Price=" + totalPrice +
                " --- Purchase Date=" + purchaseDate + "\n" +
                cust +
                prods;
    }  
}
