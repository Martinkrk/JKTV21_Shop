package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import tools.Tools;

public class GUI {
    public static String label;
    public void run(){
        Tools tool = new Tools();
        //WHEN USER STARTS AN APPLICATION
        tool.onOpen();
        
        JFrame f = new JFrame();
        
        label = "Choose an option";
        
        //LABEL 1
        JLabel l1 = new JLabel("Label", SwingConstants.CENTER);
        l1.setText(label);
        l1.setBounds(100, 50, 200, 25);
        f.add(l1);
        
        //BUTTON 1
        JButton b1 = new JButton("Browse...");
        b1.setBounds(150,100,100,25);
        f.add(b1);
        
        //BUTTON 2
        JButton b2 = new JButton("Find...");
        b2.setBounds(150,150,100,25);
        f.add(b2);
        
        //BUTTON 3
        JButton b3 = new JButton("Add...");
        b3.setBounds(150,200,100,25);
        f.add(b3);
        
        //BUTTON 4
        JButton b4 = new JButton("Products");
        b4.setBounds(150,100,100,25);
        f.add(b4);
        b4.setVisible(false);
        
        //BUTTON 5
        JButton b5 = new JButton("Customers");
        b5.setBounds(150,150,100,25);
        f.add(b5);
        b5.setVisible(false);
        
        //BUTTON 6
        JButton b6 = new JButton("Purchases");
        b6.setBounds(150,200,100,25);
        f.add(b6);
        b6.setVisible(false);
        
        //BUTTON 7
        JButton b7 = new JButton("Find a product by its Id");
        b7.setBounds(150,100,100,25);
        f.add(b7);
        b7.setVisible(false);
        
        //BUTTON 8
        JButton b8 = new JButton("Find a customer by its Id");
        b8.setBounds(150,150,100,25);
        f.add(b8);
        b8.setVisible(false);
        
        //BUTTON 9
        JButton b9 = new JButton("Find a purchase by its Id");
        b9.setBounds(150,200,100,25);
        f.add(b9);
        b9.setVisible(false);
        
        //BUTTON 10
        JButton b10 = new JButton("Product");
        b10.setBounds(150,100,100,25);
        f.add(b10);
        b10.setVisible(false);
        
        //BUTTON 11
        JButton b11 = new JButton("Purchase");
        b11.setBounds(150,150,100,25);
        f.add(b11);
        b11.setVisible(false);
        
        //BUTTON 12
        JButton b12 = new JButton("Back");
        b12.setBounds(150,250,100,25);
        f.add(b12);
        b12.setVisible(false);
        
        //LISTENERS
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b1.setVisible(false);
                b2.setVisible(false);
                b3.setVisible(false);
                b4.setVisible(true);
                b5.setVisible(true);
                b6.setVisible(true);
                b12.setVisible(true);
                label = "Choose an item to browse:";
                l1.setText(label);
    } 
        });
        
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.browseCustomers();
                b1.setVisible(false);
                b2.setVisible(false);
                b3.setVisible(false);
                b7.setVisible(true);
                b8.setVisible(true);
                b9.setVisible(true);
                b12.setVisible(true);
                
    } 
        });
        
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.browsePurchases();
                b1.setVisible(false);
                b2.setVisible(false);
                b3.setVisible(false);
                b10.setVisible(true);
                b11.setVisible(true);
                b12.setVisible(true);
    } 
        });
        
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.browseProducts();
    } 
        });
        
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.browseCustomers();
    } 
        });
        
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.browsePurchases();
    } 
        });
        
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.findProduct();
    } 
        });
        
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.findCustomer();
    } 
        });
        
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.findPurchase();
    } 
        });
        
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.createProduct();
    } 
        });
        
        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tool.createPurchase();
    } 
        });
        
        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b1.setVisible(true);
                b2.setVisible(true);
                b3.setVisible(true);
                b4.setVisible(false);
                b5.setVisible(false);
                b6.setVisible(false);
                b7.setVisible(false);
                b8.setVisible(false);
                b9.setVisible(false);
                b10.setVisible(false);
                b11.setVisible(false);
                b12.setVisible(false);
                label = "Choose an option";
                l1.setText(label);
    } 
        });
        
        //ON CLOSING THE APPLICATION WINDOW
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            tool.onClose();
            f.dispose();
            System.exit(0);
        }
    });
       
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }
}
