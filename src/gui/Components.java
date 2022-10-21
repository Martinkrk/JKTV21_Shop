package gui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class Components {
    //FRAME AND PANES
    
    JFrame f = new JFrame();
    
    JPanel pane = new JPanel();
    
    JScrollPane sp = new JScrollPane(pane);
    
    //SIZES
    int W_Width = 1000;
    int W_Height = 400;
        
    //LABELS
    
    public static String label;
    JLabel l1 = new JLabel("Label", SwingConstants.CENTER);
    
    //TEXTAREAS
    
    JTextArea ta1 = new JTextArea();
    
    //BUTTONS
    
    JButton b1 = new JButton("Search...");
    JButton b2 = new JButton("Add...");
    JButton b3 = new JButton("Products");
    JButton b4 = new JButton("Customers");
    JButton b5 = new JButton("Purchases");
    JButton b6 = new JButton("Back");
    JButton b7 = new JButton("Search");
    
    public Components(){
        
        //PANES
        
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS)); 
        
        JScrollPane sp = new JScrollPane(pane);
        
        pane.setBackground(Color.GRAY);
        
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        
        f.getContentPane().add(sp);
        
        //LABELS
        
        label = "Choose an option";
        
        l1.setText(label);
        l1.setBounds(450, 50, 200, 25);
        f.add(l1);
        
        //BUTTONS
        
        b1.setBounds(450,100,100,25); //Search...
        f.add(b1);
              
        b2.setBounds(450,150,100,25); //Add...
        f.add(b2);
        
        b3.setBounds(450,100,100,25); //Products
        f.add(b3);
        b3.setVisible(false);
        
        b4.setBounds(450,150,100,25); //Customers
        f.add(b4);
        b4.setVisible(false);
        
        b5.setBounds(450,200,100,25); //Purchases
        f.add(b5);
        b5.setVisible(false);
        
        b6.setBounds(450,250,100,25); //Back
        f.add(b6);
        b6.setVisible(false);
        
        b7.setBounds(220,20,100,20); //Search button
        f.add(b7);
        b7.setVisible(false);
        
        //TEXTAREAS
        
        ta1.setBounds(10,20,200,20);
        f.add(ta1);
        ta1.setVisible(false);
        ta1.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //FRAME
        
        f.setSize(W_Width,W_Height);
        f.setVisible(true);
    } 
}
