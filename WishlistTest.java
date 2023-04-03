import java.util.*;
public class WishlistTest {


    public static void main(String[] args) {
        System.out.println("Test Add Item:");
        testMoveToCart();
        
    }

     
    /**
     * whishlist Test:
     * checks if items from wishlist are sucessfully moved to cart and removd from wishlist
     */
    public static void testMoveToCart() {
       
        Wishlist wishlist = new Wishlist(false);
        Cart cart = new Cart();
    
        Shoe shoe = new Shoe(2, "Adidas Superstar", 80.00, 8, "Classic casual shoes", "Casual", "US 8", "White", "Adidas", "Jane Doe");
        int quantity = 2;
        int shoeId = 2;
        wishlist.addItem(shoe, quantity);

        wishlist.moveToCart(cart, shoeId);
        Map<Integer, Integer> wish = wishlist.getItems();
        Map<Integer, Integer> items = cart.getItems();

        if (wish.isEmpty() && !items.isEmpty()){
            System.out.println("Passed");
        }
        else{
            System.out.println("Failed");
        }
    }


}