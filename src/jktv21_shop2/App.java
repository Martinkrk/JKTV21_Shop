package jktv21_shop2;

import entities.Product;
import entities.Purchase;
import java.util.ArrayList;
import tools.Messages;
import tools.Tools;

public class App {
        Tools tool = new Tools();
        Messages msg = new Messages();
    public void run(){
        
        //APP START
//        tool.onOpen();
        //APP START WITH DB CONNECTION
          tool.onOpenDB();
        
        //TESTING
        tool.createDateRanges();
        
        boolean mainloop = true;
        int userInput; int userInputNested;
        
        System.out.println(msg.welcome);
        do{
            System.out.println(msg.mainMenu);
                userInput = tool.inputInt(new int[] {0,3});
                
                switch(userInput){
                    case 0:
                        mainloop = false;
                        break;
                    case 1:
                        
                        nested: do{
                        System.out.println(msg.browseMenu);
                        userInputNested = tool.inputInt(new int[] {0,3});
                            
                            switch(userInputNested){
                                case 0:
                                    break nested;
                                case 1:
                                    tool.browseProducts();
                                    break;
                                case 2:
                                    tool.browseCustomers();
                                    break;
                                case 3:
                                    tool.browsePurchases();
                                    break;
                            }
                        }while(true);
                            break;
                    case 2:
                        
                        nested: do{
                        System.out.println(msg.addMenu);
                        userInputNested = tool.inputInt(new int[] {0,3});
                        
                            switch(userInputNested){
                                case 0:
                                    break nested;
                                case 1:
                                    tool.createProduct();
                                    break;
                                case 2:
                                    tool.createCustomer();
                                    break;
                                case 3:
                                    tool.createPurchase();
                                    break;
                            }
                        }while(true);
                        
                        break;
                        
                    case 3:

                        nested: do{
                        System.out.println(msg.manageMenu);
                        userInputNested = tool.inputInt(new int[] {0,3});
                        
                            switch(userInputNested){
                                case 0:
                                    break nested;
                                case 1:
                                    tool.addBalanceToCustomer();
                                    break;
                                case 2:
                                    tool.addStock();
                                    break;
                                case 3:
                                    tool.showStatistics();
                                    break;
                            }
                        }while(true);
                        
                        break;
                }
 
        }while(mainloop);
    }
}