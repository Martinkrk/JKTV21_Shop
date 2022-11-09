package tools;

import entities.Customer;
import entities.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entities.shopArrays;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;

public class DataBaseManager {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV21_Shop2PU");
    EntityManager em = emf.createEntityManager();
    
    shopArrays ars = new shopArrays();
    
    public void saveProducts(ArrayList<Product> products){;
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        for(int i = 0; i < products.size() ;i++){
            Product prod = products.get(i);
            if(prod.getId()== null){
                em.persist(prod);
                break;
            }
        }
        tx.commit();
    }
    
    public void saveProduct(Product product){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(product);
        tx.commit();
    }
    
    public List<Product> DBExtractProducts(){
        List<Product> prods = em.createQuery("SELECT p FROM Product p").getResultList();
        return prods;
    }
    
    public void changeProduct(Product product){
        Product clone = new Product();
        clone.setName(product.getName());
        clone.setCost(product.getCost());
        clone.setAmount(product.getAmount());
        clone.setId(product.getId());
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(clone);
        tx.commit();
    }
    
    public void saveCustomer(Customer customer){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(customer);
        tx.commit();
    }
    
        public List<Customer> DBExtractCustomers(){
        List<Customer> custs = em.createQuery("SELECT p FROM Customer p").getResultList();
        return custs;
    }
    
    public void changeCustomer(Customer customer){
        Customer clone = new Customer();
        clone.setRegistered(customer.isRegistered());
        clone.setUsedDiscount(customer.isUsedDiscount());
        clone.setBalance(customer.getBalance());
        clone.setId(customer.getId());
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(clone);
        tx.commit();
    }
    
    public List<Customer> getCustomers(){
        return em.createQuery("SELECT p FROM Customer p").getResultList();
    }
}