package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean Registered;
    private boolean UsedDiscount;
    private Double balance;
    
        public Customer(boolean Registered, boolean UsedDiscount, Double balance) {
        this.Registered = Registered;
        this.UsedDiscount = UsedDiscount;
        this.balance = balance;
    }

    public Customer(boolean Registered, boolean UsedDiscount) {
        this.Registered = Registered;
        this.UsedDiscount = UsedDiscount;
        this.balance = 0.00;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public boolean isRegistered() {
        return Registered;
    }

    public void setRegistered(boolean Registered) {
        this.Registered = Registered;
    }

    public boolean isUsedDiscount() {
        return UsedDiscount;
    }

    public void setUsedDiscount(boolean UsedDiscount) {
        this.UsedDiscount = UsedDiscount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    public void addBalance(Double balance) {
        this.balance += balance;
    }
    
    public void subtractBalance(Double cost) {
        this.balance -= cost;
    }

    @Override
    public String toString() {
        return "Customer" + 
                " : Id=" + id + 
                " --- Registered=" + Registered + 
                " --- UsedDiscount=" + UsedDiscount + 
                " --- balance=" + balance;
    } 
}
