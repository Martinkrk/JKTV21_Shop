package entities;

import java.io.Serializable;

public class Customer implements Serializable {
    private static int counter = 0;
    
    private final int Id;
    private boolean Registered;
    private boolean UsedDiscount;
    private Double balance;
    
        public Customer(boolean Registered, boolean UsedDiscount, Double balance) {
        this.Id = counter++;
        this.Registered = Registered;
        this.UsedDiscount = UsedDiscount;
        this.balance = balance;
    }

    public Customer(boolean Registered, boolean UsedDiscount) {
        this.Id = counter++;
        this.Registered = Registered;
        this.UsedDiscount = UsedDiscount;
        this.balance = 0.00;
    }

    public int getId() {
        return Id;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Customer.counter = counter;
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
                " : Id=" + Id + 
                " --- Registered=" + Registered + 
                " --- UsedDiscount=" + UsedDiscount + 
                " --- balance=" + balance;
    } 
}
