/*
 * Caleb Eng
 * cps 406
 * testing user tree
 */

import java.util.ArrayList;
public class UserTreeTest {
    public static void main (String[]args){
        System.out.println("Seller Test: "+testSeller("GenName", "Woit@tmu.ca", "Woit is the best", "cps590 is the best course ever"));
        System.out.println("Admin Test: "+testAdmin("idk something", "imAnAdmin@tmu.ca", "password!"));
        System.out.println("Shopper Test no phonenumber: "+testShopper("Woits biggest fan", "1234 street", "passwordShopper", "gmai.com2"));
        System.out.println("Shopper Test phonenumber: "+testShopper("joe", "some street", "911", "passwordshopper2", "gmai.com"));
        System.out.println("User Test1: "+testUser("woit", "We love Woit", "Cps590 is great"));
        System.out.println("User Test2: "+testUser("ShadowWizardMoneyGang", "We love", "casting spells"));
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
}
