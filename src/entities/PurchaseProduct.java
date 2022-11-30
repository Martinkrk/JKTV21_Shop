package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseProduct implements Serializable {
    @Id @ManyToOne
    Purchase purchase;
    
    @Id @ManyToOne
    Product product;
    
    private int product_count;
}
