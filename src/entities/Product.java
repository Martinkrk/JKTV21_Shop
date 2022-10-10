package entities;

import java.math.BigDecimal;

public class Product {
    private static int counter = 0;
    private final int Id;
    private String Name;
    private Double Cost;

    //CONSTRUCTOR
    public Product(String name, double cost) {
        Id = counter++;
        Name = name;
        Cost = cost;
    }

    //GET AND SET
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + getId() +
                ", Name='" + getName() + '\'' +
                ", Cost=" + getCost() +
                '}';
    }
}
