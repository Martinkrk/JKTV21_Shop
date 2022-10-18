package entities;

import java.io.Serializable;

public class Customer implements Serializable {
    private static int counter = 0;
    private final int Id;
    private boolean Registered;
    private boolean UsedDiscount;

    //CONSTRUCTOR
    public Customer(boolean registered) {
        Id = counter++;
        Registered = registered;
        UsedDiscount = false;
    }

    //GET AND SET
    public int getId() {
        return Id;
    }

    public boolean isRegistered() {
        return Registered;
    }

    public void setRegistered(boolean registered) {
        Registered = registered;
    }

    public boolean isUsedDiscount() {
        return UsedDiscount;
    }

    public void setUsedDiscount(boolean usedDiscount) {
        UsedDiscount = usedDiscount;
    }

    public static void setCounter(int len){
        counter = len;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + getId() +
                ", Registered=" + isRegistered() +
                ", UsedDiscount=" + isUsedDiscount() +
                '}';
    }
}
