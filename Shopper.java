/*
 * Caleb Eng
 * 3/15/2023
 * CPS-406
 * shopper class:
 * holds all the information about a shopper
 */

 import java.util.ArrayList;
import java.util.Date;

public class Shopper extends User{
    private String address;
    private String phoneNuber;
    private ArrayList<String> paymentMethod;
    private ArrayList<Review> shoppReviews;

    //orders, currentOrders, and PastOrders
    //Possibly change shopperOrders from Orders to an arrayList containing all the orders
    private ArrayList<Orders> shopperOrders;
    private ArrayList<CurrentOrders> orderList;
    private ArrayList<PastOrders> pastOrderList;

    private Cart shopperCart;
    private Wishlist wishlist;


    /**
     * Class constructor with phone number input
     * @param addressI
     *      the users address
     * @param phoneNumberI
     *      the users phone number
     * 
     * Info for parent constructor 
     * @param nameI
     *      the users name
     * @param idI
     *      user id tag
     * @param password
     *      user account password
     * @param email 
     *      user email
     * @see User
     */
    public Shopper(String nameI, String addressI, String phoneNumberI, int idI, String password, String email){
        
        super(idI, nameI, email, password,"Shopper");
        
        this.address = addressI;
        this.phoneNuber = phoneNumberI;
        this.paymentMethod = new ArrayList<String>();
        this.shoppReviews = new ArrayList<Review>();

        //cart and whishlist to be implemented
        this.shopperCart = new Cart();
        this.wishlist = new Wishlist();
    }
    
    /**
     * Class constructor WITHOUT phone number input
     * If the user does not provide a phone number then this constructor is called
     * phoneNumber will instead be set to "". so when the user wishes to add the phone number the variable will exist
     * @param addressI
     *      the users address
     * 
     * Info for parent constructor:
     * @param nameI
     *      the users name
     * @param idI
     *      user id tag
     * @param password
     *      user account password
     * @param email 
     *      user email
     * @see User
     */
    public Shopper(String nameI, String addressI, int idI, String password, String email){
    
        super(idI, nameI, email, password,"Shopper");

        this.address = addressI;
        this.phoneNuber = "";
        this.paymentMethod = new ArrayList<String>();
        this.shoppReviews = new ArrayList<Review>();

        //cart and whishlist to be implemented
        this.shopperCart = new Cart();
        this.wishlist = new Wishlist();
    }


    //getters:
    public String getaddress(){
        return address;
    }

    public String getphoneNumber(){
        return phoneNuber;
    }

    //setters:
    public void setAddress(String addressI){
        this.address = addressI;
    }


    //other class methods:
     
    /**
     * Method to add (phone) number?
     * @param number
     *      user phone number to be changed/added?
     */
    public void addNumber(String number){
        this.phoneNuber = number;
    }

    /**
     * method to add items to the user cart
     * @param item 
     *      item to be added to the cart
     * sends the item to the cart class to add
     * @see Cart           
     */
    public void addToCart(Shoe itemI){
        this.shopperCart.addItem(itemI);
    }


    /**
     * method to add items to the wishlist
     * @param itemI
     *      item to be added to the user wishlist
     * sends the item to the wishlist class
     * @see Wishlist
     */
    public void addToWishlist(Shoe itemI){
        this.wishlist.addItem(itemI);
    }

    public void createOrder(int orderIdI, String status, double total, Date orderDate, Date estimatedDate){
        

    }





    

}
