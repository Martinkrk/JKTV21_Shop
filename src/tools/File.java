//    //WHEN USER OPENS APPLICATION
//    //READ FROM FILES
//    public void onOpen(){
//
//        //FILES
//            if(!new File("data").exists()){
//                new File("data").mkdirs();
//            }
//
//        File prods = new File("data\\products.txt");
//        File custs = new File("data\\customers.txt");
//        File purcs = new File("data\\purchases.txt");
//            
//        try{
//            if(prods.createNewFile()) System.out.println("File created");
//            if(custs.createNewFile()) System.out.println("File created"); 
//            if(purcs.createNewFile()) System.out.println("File created");
//        }
//        catch(Exception e){
//            System.err.println(e);
//        }
//        
//        if(prods.canRead() && prods.length() != 0){
//            extractProducts();
//        }
//          
//        if(custs.canRead() && custs.length() != 0){
//            extractCustomers();
//        }
//        else{
//            ars.addCustomer(new Customer(false, false));
//        }
//
//        if(purcs.canRead() && purcs.length() != 0){
//            extractPurchases();
//        }
//    }
//    
//    public void extractProducts(){
//        ArrayList<Product> arr = new ArrayList<>();
//        try{
//                FileInputStream file = new FileInputStream("data\\products.txt");
//                ObjectInputStream input = new ObjectInputStream(file);
//                
//                arr = (ArrayList<Product>) input.readObject();
//                input.close();
//        }
//        catch(Exception e){
//            System.err.println(e);
//        }
//        
//        ars.setProducts(arr);
////        Product.setCounter(arr.size());
//    }
//    
//    public void extractCustomers(){
//        ArrayList<Customer> arr = new ArrayList<>();
//        try{
//            FileInputStream file = new FileInputStream("data\\customers.txt");
//            ObjectInputStream input = new ObjectInputStream(file);
//
//            arr = (ArrayList<Customer>) input.readObject();
//            input.close();
//        }
//        catch(Exception e){
//            System.err.println(e);
//        }
//
//        ars.setCustomers(arr);
//        Customer.setCounter(arr.size());
//    }
//
//    public void extractPurchases(){
//        ArrayList<Purchase> arr = new ArrayList<>();
//        try{
//                FileInputStream file = new FileInputStream("data\\purchases.txt");
//                ObjectInputStream input = new ObjectInputStream(file);
//                
//                arr = (ArrayList<Purchase>) input.readObject();
//                input.close();
//        }
//        catch(Exception e){
//            System.err.println(e);
//        }
//        ars.setPurchases(arr);
//        Purchase.setCounter(arr.size());
//    } 
//      
//    //WHEN USER CLOSES APPLICATION
//    //WRITE TO FILES
//    public void onClose(){
//        //IF THERE ARE ELEMENTS IN PRODUCTS
//        if(ars.getProducts().size() != 0){
//            //PRODUCTS
//            try{
//                FileOutputStream fileProducts = new FileOutputStream("data\\products.txt");
//                ObjectOutputStream outputProducts = new ObjectOutputStream(fileProducts);
//
//                outputProducts.writeObject(ars.getProducts());
//                outputProducts.close();
//            }
//            catch(Exception e){
//                System.err.println(e);
//            }  
//        }
//        
//        //IF THERE ARE ELEMENTS IN CUSTOMERS
//        if(ars.getCustomers().size() != 0){
//            //CUSTOMERS
//            try{
//                FileOutputStream fileCustomers = new FileOutputStream("data\\customers.txt");
//                ObjectOutputStream outputCustomers = new ObjectOutputStream(fileCustomers);
//
//                outputCustomers.writeObject(ars.getCustomers());
//                outputCustomers.close();
//            }
//            catch(Exception e){
//                System.err.println(e);
//            }  
//        }
//        
//        //IF THERE ARE ELEMENTS IN PURCHASES
//        if(ars.getPurchases().size() != 0){
//            //PURCHASES
//            try{
//                FileOutputStream filePurchases = new FileOutputStream("data\\purchases.txt");
//                ObjectOutputStream outputPurchases = new ObjectOutputStream(filePurchases);
//
//                outputPurchases.writeObject(ars.getPurchases());
//                outputPurchases.close();
//            }
//            catch(Exception e){
//                System.err.println(e);
//            }  
//        }
//        
//        //SAVE STATS
//        try{
//            FileOutputStream fileStats = new FileOutputStream("data\\statistics.txt");
//            ObjectOutputStream outputStats = new ObjectOutputStream(fileStats);
//
//            outputStats.writeObject(stats);
//            outputStats.close();
//            }
//            catch(Exception e){
//                System.err.println(e);
//            }
//    }