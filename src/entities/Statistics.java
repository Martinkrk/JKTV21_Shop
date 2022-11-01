package entities;

import java.io.Serializable;

public class Statistics implements Serializable {
    
    private int totalProductsSold;
    private int totalRegisteredCustomers;
    private Double totalEarnings;

    public Statistics() {
        this.totalProductsSold = 0;
        this.totalRegisteredCustomers = 0;
        this.totalEarnings = 0.00;
    }

    public int getTotalProductsSold() {
        return totalProductsSold;
    }

    public void setTotalProductsSold(int totalProductsSold) {
        this.totalProductsSold = totalProductsSold;
    }
    
    public void addTotalRegisteredCustomers() {
        this.totalRegisteredCustomers += 1;
    }
    
    public void addTotalProductsSold() {
        this.totalProductsSold += 1;
}

    public int getTotalRegisteredCustomers() {
        return totalRegisteredCustomers;
    }

    public void setTotalRegisteredCustomers(int totalRegisteredCustomers) {
        this.totalRegisteredCustomers = totalRegisteredCustomers;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }
    
    public void addTotalEarnings(Double total){
        this.totalEarnings += total;
    }

    @Override
    public String toString() {
        return "eh...";
    }
    
    
}
