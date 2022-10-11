package entities;

public class Customer {
    private static int counter = -1;
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

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + getId() +
                ", Registered=" + isRegistered() +
                ", UsedDiscount=" + isUsedDiscount() +
                '}';
    }
}
