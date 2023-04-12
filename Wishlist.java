/*
The Wishlist class extends the Cart class and represents a collection of items that a user wants to buy in the future.
This class allows the user to view their wishlist, move items from their wishlist to their cart, and remove items from their wishlist.
The Wishlist class contains a HashMap to store the items in the wishlist, as well as a boolean flag to indicate whether the wishlist is private or not.
*/

import java.util.HashMap;
import java.util.Map;

public class Wishlist {
    private Map<Integer, Integer> items; // map to store the shoe IDs and their corresponding quantities
    private Map<Integer, Double> prices; // map to store the shoe IDs and their corresponding prices
    private boolean isPrivate; // boolean flag indicating whether the wishlist is private or not

    /*
     * Constructor for the class
     *
     * @param isPrivate
     *      The boolean flag indicating whether the wishlist is private or not
     */
    public Wishlist(boolean isPrivate) {
        items = new HashMap<>(); // initialize the map of items
        prices = new HashMap<>(); // initialize the map of prices
        this.isPrivate = isPrivate; // set the boolean flag for private wishlist
    }

    /*
     * Returns whether the wishlist is private or not
     *
     * @return
     *      True if the wishlist is private, false otherwise
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /*
     * Sets the boolean flag indicating whether the wishlist is private or not
     *
     * @param isPrivate
     *      The boolean flag indicating whether the wishlist is private or not
     */
    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    /*
     * Checks whether the wishlist is empty or not
     *
     * @return
     *      True if the wishlist is empty, false otherwise
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /*
     * Adds a shoe to the wishlist
     *
     * @param shoe
     *      The shoe object to be added to the wishlist
     * @param quantity
     *      The quantity of the shoe to be added to the wishlist
     */
    public void addItem(Shoe shoe, int quantity) {
        if (items.containsKey(shoe.getId())) { // if the shoe already exists in the wishlist
            items.put(shoe.getId(), items.get(shoe.getId()) + quantity); // update the quantity of the shoe
        } else { // if the shoe does not exist in the wishlist
            items.put(shoe.getId(), quantity); // add the shoe to the wishlist with the given quantity
        }
        prices.put(shoe.getId(), shoe.getPrice()); // store the price of the shoe in the prices map
    }

    /*
     * Removes a shoe from the wishlist
     *
     * @param shoeId
     *      The ID of the shoe to be removed from the wishlist
     */
    public void removeItem(int shoeId) {
        items.remove(shoeId); // remove the shoe from the items map
        prices.remove(shoeId); // remove the price of the shoe from the prices map
    }

    /*
     * Returns the map of shoes and their quantities in the wishlist
     *
     * @return
     *      The map of shoes and their quantities in the wishlist
     */
    public Map<Integer, Integer> getItems() {
        return items;
    }

    /*
     * Returns the total number of items in the wishlist
     *
     * @return
     *      The total number of items in the wishlist
     */
    public int getTotalItems() {
        int totalItems = 0;
        for (int quantity : items.values()) { // loop through all the quantities in the items map
            totalItems += quantity; // add the quantity to the total number of items
        }
        return totalItems;
    }

    /*
    * Displays the shoes and their corresponding quantities and prices in the wishlist
    */
    public String display() {
        String output = "";
        if (items.isEmpty()) { // if the wishlist is empty
            output += "Your wishlist is empty.";
        } else { // if the wishlist is not empty
            output += "Your wishlist contains:\n";
            for (int shoeId : items.keySet()) { // loop through all the shoe IDs in the items map
                int quantity = items.get(shoeId); // get the quantity of the shoe
                double price = prices.get(shoeId); // get the price of the shoe
                output += "- Shoe ID: " + shoeId + ", Quantity: " + quantity + ", Price: $" + price + "\n"; // print the shoe ID, quantity, and price
            }
            output += "Total items: " + getTotalItems() + "\n"; // print the total number of items in the wishlist
        }
        return output;
    }

        /*
    * Moves a shoe from the wishlist to the cart
    *
    * @param cart
    *      The cart object to which the shoe should be moved
    * @param shoeId
    *      The ID of the shoe to be moved to the cart
    */
    public void moveToCart(Cart cart, int shoeId) {
        if (!items.containsKey(shoeId)) { // if the shoe does not exist in the wishlist
            System.out.println("Shoe not found in wishlist.");
        } else { // if the shoe exists in the wishlist
            int quantity = items.get(shoeId); // get the quantity of the shoe
            double price = prices.get(shoeId); // get the price of the shoe
            cart.wishAddItem(shoeId, quantity, price); // add the shoe to the cart
            items.remove(shoeId); // remove the shoe from the wishlist
            prices.remove(shoeId); // remove the price of the shoe from the prices map
        }
    }
}