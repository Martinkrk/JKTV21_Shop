package entities;

import java.io.Serializable;

public class Product implements Serializable{
    private static int counter = 0;
    private final int Id;
    private String Name;
    private Double Cost;
    private Integer amount;
   
    public Product(String Name, Double Cost, Integer amount) {
        this.Id = counter++;
        this.Name = Name;
        this.Cost = Cost;
        this.amount = amount;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Product.counter = counter;
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
    
    public void addStock(Integer amount){
        this.amount += amount;
    }
    
    public void substractStock(){
        this.amount -= 1;
    }

    @Override
    public String toString() {
        return "Product" + 
                " : Id=" + Id + 
                " --- Name=" + Name + 
                " --- Cost=" + Cost + 
                " --- In stock=" + amount + "\n";
    }
}
