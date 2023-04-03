import java.util.*;

public class CartTest {

    public static void main(String[] args) {
        System.out.println("Test Add Item:");
        testAddItem();
        System.out.println("Test Wishlist Add Item:");
        testWishAddItem();
        System.out.println("Test Remove Item:");
        testRemoveItem();
        System.out.println("Test Get Total Items:");
        testGetTotalItems();
        System.out.println("Test Get Total Cost:");
        testGetTotalCost();
        
    }


    /**
     * cart Test 1:
     * checks if shoe objects are succesfully added to the cart
     */
    public static void testAddItem() {
        Cart cart = new Cart();
        Shoe shoe = new Shoe(2, "Adidas Superstar", 80.00, 8, "Classic casual shoes", "Casual", "US 8", "White", "Adidas", "Jane Doe");
        Shoe shoe2 = new Shoe(3, "Nike Air Max 90", 120.00, 10, "Air cushioned running shoes", "Running", "US 10", "Black/Red", "Nike", "John Smith");
        Shoe shoe3 = new Shoe(4, "Nike Air Max 90", 160.00, 10, "Air cushioned running shoes", "Running", "US 10", "Black/Red", "Nike", "John Smith");


        cart.addItem(shoe, 2);
        cart.addItem(shoe2, 2);
        cart.addItem(shoe3, 2);
        Map<Integer, Integer> items = cart.getItems();

        int expeceted = 3;
        int actual = items.size();
        if (actual == expeceted) {
            System.out.println("Passed");
        } 
        else{
            System.out.println("Failed");
        }
    }


   /**
     * cart Test 2:
     * checks if items from wishlist are addeed succesfully to the cart
     */
    public static void testWishAddItem() {
        Cart cart = new Cart();
        cart.wishAddItem(2, 3, 50.0);
        cart.wishAddItem(5, 3, 10.0);
        Map<Integer, Integer> items = cart.getItems();
        
        int expeceted = 2;
        int actual = items.size();
        if (actual == expeceted) {
            System.out.println("Passed");
        } 
        else{
            System.out.println("Failed");
        }
    }


    /**
     * cart Test 3:
     * checks if items from cart are removed succesfully
     */
    public static void testRemoveItem() {
        Cart cart = new Cart();
        Shoe shoe = new Shoe(2, "Adidas Superstar", 80.00, 8, "Classic casual shoes", "Casual", "US 8", "White", "Adidas", "Jane Doe");
        cart.addItem(shoe, 2);
        cart.removeItem(2);
        Map<Integer, Integer> items = cart.getItems();
        boolean expected = true;
        boolean actual = items.isEmpty();
        if (actual == expected) {
            System.out.println("Passed");
        } 
        else{
            System.out.println("Failed");
        }
    }

    
    /**
     * cart Test 4:
     * checks if the correct quantity of the cart is being calculated
     */
    public static void testGetTotalItems() {
        Cart cart = new Cart();
        Shoe shoe = new Shoe(2, "Adidas Superstar", 80.00, 8, "Classic casual shoes", "Casual", "US 8", "White", "Adidas", "Jane Doe");
        Shoe shoe2 = new Shoe(5, "Nike Air Max 97", 150.00, 10, "Premium running shoes", "Running", "US 10", "Black/Grey", "Nike", "John Smith");        cart.addItem(shoe, 2);
        cart.addItem(shoe2, 1);
        
        int actual = cart.getTotalItems();
        int expeceted = 3;
        if (actual == expeceted) {
            System.out.println("Passed");
        } 
        else{
            System.out.println("Failed");
        }
    }

        
    /**
     * cart Test 5:
     * checks if the correct cost of the cart is being calculated
     */
    public static void testGetTotalCost() {
        Cart cart = new Cart();
        Shoe shoe = new Shoe(2, "Adidas Superstar", 80.00, 8, "Classic casual shoes", "Casual", "US 8", "White", "Adidas", "Jane Doe");
        Shoe shoe2 = new Shoe(5, "Nike Air Max 97", 150.00, 10, "Premium running shoes", "Running", "US 10", "Black/Grey", "Nike", "John Smith");        cart.addItem(shoe, 2);
        cart.addItem(shoe, 2);
        cart.wishAddItem(2, 3, 50.0);
        
        
        double actual = cart.getTotalCost();
        double expeceted = 560.00;
        if (actual==expeceted) {
            System.out.println("Passed");
        } 
        else{
            System.out.println("Failed");
        }
    }

}