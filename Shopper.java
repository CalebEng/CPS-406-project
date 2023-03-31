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
    private String phoneNumber;
    private ArrayList<String> paymentMethod;
    private ArrayList<Review> shoppReviews;

    //orders, currentOrders, and PastOrders
    //Possibly change shopperOrders from Orders to an arrayList containing all the orders
    private ArrayList<Order> shopperOrders;
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
    public Shopper(String nameI, String addressI, String phoneNumberI, String password, String email){
        
        super(nameI, email, password,"Shopper");
        
        this.address = addressI;
        this.phoneNumber = phoneNumberI;
        this.paymentMethod = new ArrayList<String>();
        this.shoppReviews = new ArrayList<Review>();

        //cart and whishlist to be implemented
        this.shopperCart = new Cart();
        this.wishlist = new Wishlist(true);
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
    public Shopper(String nameI, String addressI, String password, String email){
    
        super(nameI, email, password,"Shopper");

        this.address = addressI;
        this.phoneNumber = "";
        this.paymentMethod = new ArrayList<String>();
        this.shoppReviews = new ArrayList<Review>();

        //cart and whishlist to be implemented
        this.shopperCart = new Cart();
        this.wishlist = new Wishlist(true);
    }


    //getters:
    public String getaddress(){
        return this.address;
    }

    public String getphoneNumber(){
        return this.phoneNumber;
    }

    public Cart getCart(){
        return this.shopperCart;
    }

    public Wishlist getWishlist(){
        return this.wishlist;
    }

    public ArrayList<String> getPayments(){
        return this.paymentMethod;
    }

    public ArrayList<CurrentOrders> getOrderList(){
        return this.orderList;
    }

    public ArrayList<Order> getOrder(){
        return this.shopperOrders;
    }

    public ArrayList<PastOrders> getPastOrders(){
        return this.pastOrderList;
    }

    public ArrayList<Review> getReviews(){
        return this.shoppReviews;
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
        this.phoneNumber = number;
    }

    /**
     * method to add items to the user cart
     * @param item 
     *      item to be added to the cart
     * @param count
     *      how many of said item to be added
     * sends the item to the cart class to add
     * @see Cart           
     */
    public void addToCart(Shoe itemI, int count){
        this.shopperCart.addItem(itemI,count);
    }

    /**
     * removes an item from the user cart
     * @param itemI
     *      id of the item to be removed from the cart
     * @see Cart
     */
    public void removeFromCart(int itemI){
        this.shopperCart.removeItem(itemI);
    }

    /**
     * method to add items to the wishlist
     * @param itemI
     *      item to be added to the user wishlist
     * @param count 
     *      how much of the item to add to the wishlist
     * sends the item to the wishlist class
     * @see Wishlist
     */
    public void addToWishlist(Shoe itemI,int count){
        this.wishlist.addItem(itemI,count);
    }

    /**
     * removes an item from the users wishlist
     * @param itemI
     *      id of the item to be removed
     * @see Wishlist
     */
    public void removeFromWishlist(int itemI){
        this.wishlist.removeItem(itemI);
    }

    /**
     * creates a new order and stores in the the user variable shopper orders
     * sends the orders class a shopper cart item holding all of the shoes the user wishes to buy and clears the current cart.
     * @return the order that was created
     * @see Orders
     */
    public Order placeOrder(){
        Order temp = new Order(this.shopperCart);
        shopperOrders.add(temp);
        this.shopperCart = new Cart();
        return temp;

    }

    /**
     * deletes an order from the users orderlist
     * @param orderID
     *      the order id the user wishes to delete/cancel
     * @returns true if the order was found and canceled, false otherwise
     */
    public boolean cancelOrder(int orderID){
        for(int i =0; i<this.shopperOrders.size();i++){
            if(this.shopperOrders.get(i).getOrderId() == orderID){
                this.shopperOrders.remove(i);
                return true;
            }
        }
        return false;
    }


    /**
     * to string for the shopper class
     * @returns the shopper information 
     */
    public String toString(){
        return "Acc name: "+this.getName()+"\nAcc ID: "+this.getId()+"\nAcc email: "+this.getEmail()+"\nAcc password: "+this.getPassword()+"\nAcc type: "+this.getType()+"\nPhone number: "+this.getphoneNumber()
        +"\nAddress: "+this.getaddress()+"\nShopping cart: "+this.shopperCart+"\nWishlist: "+this.wishlist+"\nPayment methods: "+this.paymentMethod+"\nOrderList: "+this.orderList+"\nPast orders: "+this.pastOrderList
        +"Shopper orders: "+this.shopperOrders+"\nReviews: "+this.shoppReviews;
    }

    

}
