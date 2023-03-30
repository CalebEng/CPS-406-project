/*
 * Caleb Eng
 * cps 406
 * testing user tree
 */

public class UserTreeTest {
    public static void main (String[]args){
        System.out.println("Seller Test: "+testSeller("GenName", "Woit@tmu.ca", "Woit is the best", "cps590 is the best course ever"));
        System.out.println("Admin Test: "+testAdmin("idk something", "imAnAdmin@tmu.ca", "password!"));
        System.out.println("Shopper Test no phonenumber: "+testShopper("Woits biggest fan", "1234 street", "passwordShopper", "gmai.com2"));
        System.out.println("Shopper Test phonenumber: "+testShopper("joe", "some street", "911", "passwordshopper2", "gmai.com"));
        System.out.println("User Test1: "+testUser("woit", "We love Woit", "Cps590 is great"));
        System.out.println("User Test2: "+testUser("ShadowWizardMoneyGang", "We love", "casting spells"));
        System.out.println("User Methods Test: "+testUserMethods("woit", "We love Woit", "Cps590 is great"));
        System.out.println("Admin Methods Test: "+testAdminMethods("idk something", "imAnAdmin@tmu.ca", "password!"));
      //System.out.println("Seller Methods Test: "+testSellerMethods("GenName", "Woit@tmu.ca", "Woit is the best", "cps590 is the best course ever"));
        System.out.println("Shopper Methods Test: "+testShopperMethods("joe", "some street", "911", "passwordshopper2", "gmai.com"));
      }

    /**
     * Creates and tests the output of an admin
     * @param name
     * @param email
     * @param password
     * @return true if the toString is the correct output and false otherwise
     */
    public static boolean testAdmin(String name, String email, String password){
      Admin  adTest = new Admin(name,email,password);
      return (adTest.toString().equals("Acc name: "+name+"\nAcc ID: "+adTest.getId()+"\nAcc email: "+email+"\nAcc password: "+password+"\nAcc type: "+"Admin"));
    }

 
     /**
      * creates and test the output of a user
      * @param name
      * @param email
      * @param password
      * @return true if the toString is the correct output and false otherwise
      */
    public static boolean testUser(String name, String email,String password){
      User userTest = new User(name,email,password,"User");
      return (userTest.toString().equals("Acc name: "+name+"\nAcc ID: "+userTest.getId()+"\nAcc email: "+email+"\nAcc password: "+password+"\nAcc type: "+"User"));
    }

    /**
     * creates and test the output of a shopper without phonenumber input
     * @param name
     * @param address
     * @param password
     * @param email
     * @return true if the output matches that of the expected and false otherwise
     */
    public static boolean testShopper(String name, String address, String password, String email){
      Shopper shopTest = new Shopper(name, address, password, email);
      return (shopTest.toString().equals("Acc name: "+name+"\nAcc ID: "+shopTest.getId()+"\nAcc email: "+email+"\nAcc password: "+password+"\nAcc type: "+"Shopper"+"\nPhone number: "+ ""
      +"\nAddress: "+address+"\nShopping cart: "+shopTest.getCart()+"\nWishlist: "+shopTest.getWishlist()+"\nPayment methods: "+shopTest.getPayments()+"\nOrderList: "+shopTest.getOrder()+"\nPast orders: "+shopTest.getPastOrders()
      +"Shopper orders: "+shopTest.getOrder()+"\nReviews: "+shopTest.getReviews()));
    }
    /**
     * creates and test the output of a shopper without phonenumber input
     * @param name
     * @param address
     * @param password
     * @param email
     * @return true if the output matches that of the expected and false otherwise
     */
    public static boolean testShopper(String name, String address, String password, String email,String phoneNumber){
      Shopper shopTest = new Shopper(name, address, phoneNumber, password, email);
      return (shopTest.toString().equals("Acc name: "+name+"\nAcc ID: "+shopTest.getId()+"\nAcc email: "+email+"\nAcc password: "+password+"\nAcc type: "+"Shopper"+"\nPhone number: "+ phoneNumber
      +"\nAddress: "+address+"\nShopping cart: "+shopTest.getCart()+"\nWishlist: "+shopTest.getWishlist()+"\nPayment methods: "+shopTest.getPayments()+"\nOrderList: "+shopTest.getOrder()+"\nPast orders: "+shopTest.getPastOrders()
      +"Shopper orders: "+shopTest.getOrder()+"\nReviews: "+shopTest.getReviews()));
    }

    /**
     * creates and tests the output of a seller
     * @param name
     * @param email
     * @param password
     * @param des
     * @return true if the toString matches expected output and false otherwise
     */
    public static boolean testSeller(String name,String email,String password, String des){
      Seller sellTest = new Seller(name, email, password, des);
      return sellTest.toString().equals("Acc name: "+name+"\nAcc ID: "+sellTest.getId()+"\nAcc email: "+email+"\nAcc password: "+password+"\nAcc type: "+"Seller"+"\nDescription: "+des
      +"\nShoe list: "+sellTest.getShoeList()+"\nOrders list: "+sellTest.viewProductBacklog()+"\nShipped Orders: " +sellTest.viewShippedOrders());
    }


    /**
     * tests the inner methods of the user class
     * @param name
     * @param email
     * @param password
     * @return true if it passes all of the tests, false otherwise
     */
    public static boolean testUserMethods(String name, String email,String password){
      User userTest = new User(name,email,password,"User");
      int passed = 0;
      if(userTest.getEmail().equals(email)){
        passed +=1;
      }
      userTest.setEmail("babaYaga");
      if(userTest.getEmail().equals("babaYaga")){
        passed+=1;
      }
      userTest.setPassword("testing123");
      if(userTest.getPassword().equals("testing123")){
        passed+=1;
      }
      userTest.setHasAccount(true);
      if(userTest.getHasAccount()==true){
        passed+=1;
      }
      userTest.setHasAccount(false);
      if(userTest.getHasAccount()==false){
        passed+=1;
      }
      userTest.setID(123);
      if(userTest.getId()==123){
        passed+=1;
      }
      if(userTest.login(123, password)== false){
        passed+=1;
      }
      if(userTest.login(123, "testing123")== true){
        passed+=1;
      }
      if(userTest.isLoggedIn()==true){
        passed+=1;
      }
      userTest.logout();
      if(userTest.isLoggedIn()==false){
        passed+=1;
      }
      
      if(passed ==10){
        return true;
      }
      else return false;
    }


    /**
     * tests the inner methods of the admin class
     * @param name
     * @param email
     * @param password
     * @return true if admin passes all of the tests, false otherwise
     */
    public static boolean testAdminMethods(String name, String email, String password){
      Admin  adTest = new Admin(name,email,password);
      Seller sellTest = new Seller("name", "email", "password", "this is a test");
      int checked =0;
      boolean check1 = testUserMethods(name,email,password);
      adTest.addShoe(new Shoe(123, "some kinda shoe", 50.6, 50, "some shoe thing", "running", "10", "black", "someone made this", "Admin"),sellTest);

      if(adTest.removeShoe(12,sellTest)==false){
        checked++;
      }
      if(adTest.removeShoe(123,sellTest)==true){
        checked++;
      }
      if(check1 ==true && checked ==2){
        return true;
      }
      else return false;
    }

    /**
     * Tests the inner methods of the seller class
     * @param name
     * @param email
     * @param password
     * @param des
     * @return true if passes all the tests, false otherwise
     */
    public static boolean testSellerMethods(String name,String email,String password, String des){
      Seller sellTest = new Seller(name, email, password, des);
      Cart temp = new Cart();
      int checked = 0;
      
      if(sellTest.getShoeList().size()==0){
        checked++;
      }
      
      sellTest.addShoe(new Shoe(123, "some kinda shoe", 50.6, 50, "some shoe thing", "running", "10", "black", "someone made this", "Seller"));
      if(sellTest.getShoeList().size()==1){
        checked++;
      }

      if(sellTest.removeShoe(12)==false){
        checked++;
      }

      if(sellTest.viewShoe(12)==null){
        checked++;
      }
      if(sellTest.viewShoe(123)!=null){
        checked++;
      }
      sellTest.addStock(1,123);
      if(sellTest.viewShoe(123).getStockCount()==51){
        checked++;
      }
      sellTest.removeStock(1,123);
      if(sellTest.viewShoe(123).getStockCount()==50){
        checked++;
      }
      if(sellTest.removeShoe(123)==true){
        checked++;
      }

      temp.addItem(new Shoe(123, "some kinda shoe", 50.6, 50, "some shoe thing", "running", "10", "black", "someone made this", "Seller"),1);

      Order tempOrd = new Order(temp);

      sellTest.addOrder(tempOrd);

      if(sellTest.viewProductBacklog().size()==1){
        checked++;
      }
      sellTest.shipOrder(tempOrd.getOrderId());
      if(sellTest.viewProductBacklog().size()==0 && sellTest.viewShippedOrders().size()==1){
        checked++;
      }

      if(checked ==10){
        return true;
      }
      else return false;
    }


    /**
     * Tests the inner methods of the shopper class
     * @param name
     * @param address
     * @param password
     * @param email
     * @param phoneNumber
     * @return true if passes all the test, false otherwise
     */
    public static boolean testShopperMethods(String name, String address, String password, String email,String phoneNumber){
      int checked = 0;
      if(checked ==10){
        return true;
      }
      else return false;
    }
}
