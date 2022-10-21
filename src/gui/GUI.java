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
                toggleMainMenu(false);
                toggleSelectionMenu(true);
                c.l1.setText("Choose an item to search:");
    } 
        });
        
        c.b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adding = true;
                toggleMainMenu(false);
                toggleSelectionMenu(true);
                c.l1.setText("Choose an item to add:");
    } 
        });
        
            c.b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searching || adding){
                    toggleSelectionMenu(false);
                    c.l1.setVisible(false);
                    if(searching){
                        showList(0);
                    }
                    else if(adding){
                        
                    }
                    else{
                        System.err.println("ERROR, not searching or adding? | b3 inner");
                    }
                }
                else{
                    System.err.println("ERROR, not searching or adding? | b3 outer");
                }
    } 
        });
                
            c.b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

    } 
        });
                                
            c.b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
    } 
        });
            
            c.b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchMenu || addMenu){
                    c.l1.setVisible(true);
                 return;   
                }
                else if(searching || adding){
                    toggleSelectionMenu(false);
                    toggleMainMenu(true);
                    c.l1.setText("Choose an option");
                    if(searching){
                        searching = false;
                    }
                    else if(adding){
                        adding = false;
                    }
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
    
    public void toggleMainMenu(boolean view){
        c.b1.setVisible(view);
        c.b2.setVisible(view);
    }
    
    public void toggleSelectionMenu(boolean view){
        c.b3.setVisible(view);
        c.b4.setVisible(view);
        c.b5.setVisible(view);
        c.b6.setVisible(view);
    }
    
    public void showList(int item){
        
    }
}
