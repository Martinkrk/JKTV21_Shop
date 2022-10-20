package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tools.Tools;

public class GUI {
    
    //LABELS ARRAY
    ArrayList<JLabel> labelArray = new ArrayList<>();
    
     //COMPONENTS INIT
    Components c = new Components();
    
    //BOOLS
    boolean searching = false;
    boolean adding = false;
    boolean searchMenu = false;
    boolean addMenu = false; 
    
    public void run(){
        Tools tool = new Tools();
        //WHEN USER STARTS AN APPLICATION
        tool.onOpen();
        
        
        //LISTENERS
        
        c.b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searching = true;
                itemsAction();
                c.label = "Choose an item to browse:";
                c.l1.setText(c.label);
    } 
        });
        
        c.b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adding = true;
                itemsAction();
                c.label = "Choose an item to add:";
                c.l1.setText(c.label);
    } 
        });
        
            c.b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(adding){
                    tool.createProduct();
                }
                else if(searching){
                    
                    ifSearching();
                    
                    int i = 0;
                    for(String s : tool.browseProducts()){
                        labelArray.add(new JLabel(s));
                        labelArray.get(labelArray.size() - 1).setBounds(10, (i*20+50), 40, 20);
                        c.pane.add(labelArray.get(labelArray.size() - 1));
                        i++;
                    };
                    searchBar();
                }
    } 
        });
                
            c.b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(adding){
                    tool.createCustomer();
                }
                else if(searching){
                    
                    ifSearching();
                    
                    int i = 0;
                    for(String s : tool.browseCustomers()){
                        labelArray.add(new JLabel(s));
                        labelArray.get(labelArray.size() - 1).setBounds(10, (i*20+50), 40, 20);
                        c.pane.add(labelArray.get(labelArray.size() - 1));
                        i++;
                    };
                    searchBar();
                }
    } 
        });
                                
            c.b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideSelectionMenu();
    } 
        });
            
            c.b6.addActionListener(new ActionListener() {
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
        c.f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        c.f.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            tool.onClose();
            c.f.dispose();
            System.exit(0);
        }
    });
       
    }
    
    public void toMainMenu(){
        searching = false;
        adding = false;
        c.b1.setVisible(true);
        c.b2.setVisible(true);
        c.b3.setVisible(false);
        c.b4.setVisible(false);
        c.b5.setVisible(false);
        c.b6.setVisible(false);
        c.label = "Choose an option";
        c.l1.setText(c.label);
    }
    
    public void hideSelectionMenu(){
        c.b3.setVisible(false);
        c.b4.setVisible(false);
        c.b5.setVisible(false);
        c.b6.setVisible(false);
    }
    
    public void backToSelection(){
        searchMenu = false;
        addMenu = false;
        c.l1.setVisible(true);
        c.b7.setVisible(false);
        c.b6.setBounds(450,250,100,25);
        c.pane.setVisible(false);
        labelArray.clear();
        c.b3.setVisible(true);
        c.b4.setVisible(true);
        c.b5.setVisible(true);
        c.ta1.setVisible(false);
    }
    
    public void itemsAction(){
        c.b1.setVisible(false);
        c.b2.setVisible(false);
        c.b3.setVisible(true);
        c.b4.setVisible(true);
        c.b5.setVisible(true);
        c.b6.setVisible(true);
    }
    
    public void searchBar(){
        c.ta1.setVisible(true);
        c.b7.setVisible(true);
        c.b6.setBounds(330, 20, 100, 20);
        c.b6.setVisible(true);
    }
    
    public void ifSearching(){
        searchMenu = true;
                    
        //LIST
        hideSelectionMenu();
        c.l1.setVisible(false);
        c.sp.setBounds(10, 60, c.W_Width, c.W_Height-97);
        c.pane.setVisible(true);
    }
}
