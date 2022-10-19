package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import tools.Tools;

public class GUI {
    
    //BUTTONS
    JButton b1 = new JButton("Search...");
    JButton b2 = new JButton("Add...");
    JButton b3 = new JButton("Products");
    JButton b4 = new JButton("Customers");
    JButton b5 = new JButton("Purchases");
    JButton b6 = new JButton("Back");
    JButton b7 = new JButton("Search");
    JLabel l1 = new JLabel("Label", SwingConstants.CENTER);
    
    //LABELS ARRAY
    ArrayList<JLabel> labelArray = new ArrayList<>();
    
    //PANE
    JPanel pane = new JPanel();
    
    //SIZES
    int W_Width = 1000;
    int W_Height = 400;

    public static String label;
    boolean searching = false;
    boolean adding = false;
    boolean searchMenu = false;
    boolean addMenu = false;
    
   //START
    
    public void run(){
        Tools tool = new Tools();
        //WHEN USER STARTS AN APPLICATION
        tool.onOpen();
        
        JFrame f = new JFrame();
        
        
        label = "Choose an option";
        //LABEL 1
        l1.setText(label);
        l1.setBounds(100, 50, 200, 25);
        f.add(l1);
        
        //BUTTON 1
        b1.setBounds(150,100,100,25);
        f.add(b1);
              
        //BUTTON 2
        b2.setBounds(150,150,100,25);
        f.add(b2);
        
        //BUTTON 3
        b3.setBounds(150,100,100,25);
        f.add(b3);
        b3.setVisible(false);
        
        //BUTTON 4
        b4.setBounds(150,150,100,25);
        f.add(b4);
        b4.setVisible(false);
        
        //BUTTON 5
        b5.setBounds(150,200,100,25);
        f.add(b5);
        b5.setVisible(false);
        
        //BUTTON 6
        b6.setBounds(150,250,100,25);
        f.add(b6);
        b6.setVisible(false);
        
        //SEARCH
        
        //Search Bar
        
        JTextArea ta1 = new JTextArea();
        ta1.setBounds(10,20,200,20);
        f.add(ta1);
        ta1.setVisible(false);
        ta1.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //BUTTON "SEARCH"
        
        b7.setBounds(220,20,100,20);
        f.add(b7);
        b7.setVisible(false);
        
        //List
        
//        pane.setBackground(Color.gray);   //TO SEE IT FOR TESTING PURPOSES
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        f.getContentPane().setLayout(new FlowLayout());  
        
        JScrollPane sp = new JScrollPane(pane);
        
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        
        f.getContentPane().add(sp);
                
        //LISTENERS
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searching = true;
                b1.setVisible(false);
                b2.setVisible(false);
                b3.setVisible(true);
                b4.setVisible(true);
                b5.setVisible(true);
                b6.setVisible(true);
                label = "Choose an item to browse:";
                l1.setText(label);
    } 
        });
        
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adding = true;
                b1.setVisible(false);
                b2.setVisible(false);
                b3.setVisible(true);
                b4.setVisible(true);
                b5.setVisible(true);
                b6.setVisible(true);
                label = "Choose an item to add:";
                l1.setText(label);
    } 
        });
        
            b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(adding){
                    tool.createProduct();
                }
                else if(searching){
                    searchMenu = true;
                    
                    //LIST
                    hideSelectionMenu();
                    l1.setVisible(false);
                    sp.setBounds(10, 60, W_Width-25, W_Height-97);
                    pane.setVisible(true);
                    int i = 0;
                    for(String s : tool.browseProducts()){
                        labelArray.add(new JLabel(s));
                        labelArray.get(labelArray.size() - 1).setBounds(10, (i*20+50), 40, 20);
                        pane.add(labelArray.get(labelArray.size() - 1));
                        i++;
                    };
                    
                    //SEARCH BAR
                    ta1.setVisible(true);
                    b7.setVisible(true);
                    b6.setBounds(330, 10, 100, 20);
                    b6.setVisible(true);
                }
    } 
        });
                
            b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideSelectionMenu();
    } 
        });
                                
            b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideSelectionMenu();
    } 
        });
            
            b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchMenu || addMenu){
                    backToSelection();
                }
                else{
                    toMainMenu();
                }
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
       
        f.setSize(W_Width,W_Height);
        f.setLayout(null);
        f.setVisible(true);
    }
    
    public void toMainMenu(){
        searching = false;
        adding = false;
        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(false);
        b4.setVisible(false);
        b5.setVisible(false);
        b6.setVisible(false);
        label = "Choose an option";
        l1.setText(label);
    }
    
    public void hideSelectionMenu(){
        b3.setVisible(false);
        b4.setVisible(false);
        b5.setVisible(false);
        b6.setVisible(false);
    }
    
    public void backToSelection(){
        b7.setVisible(false);
        b6.setBounds(150,250,100,25);
        pane.setVisible(false);
        labelArray.clear();
        b3.setVisible(true);
        b4.setVisible(true);
        b5.setVisible(true);
        ta1.set
    }
}
