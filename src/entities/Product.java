package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private Double Cost;
    private Integer amount;
//    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE});
   
    public Product(String Name, Double Cost, Integer amount) {
        this.Name = Name;
        this.Cost = Cost;
        this.amount = amount;
    }
    
    public Product(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Double getCost() {
        return Cost;
    }

    public void setCost(Double Cost) {
        this.Cost = Cost;
    }
    
    public int getAmount(){
        return amount;
    }
    
    public void setAmount(Integer amount){
        this.amount = amount;
    }
    
    public void addStock(Integer amount){
        this.amount += amount;
    }
    
    public void substractStock(){
        this.amount -= 1;
    }

    @Override
    public String toString() {
        return "Product" + 
                " : Id=" + id + 
                " --- Name=" + Name + 
                " --- Cost=" + Cost + 
                " --- In stock=" + amount + "\n";
    }
}
