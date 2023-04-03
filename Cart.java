/**
*Represents a shopping cart that stores a map of shoe IDs to their respective quantities
*and prices. Provides methods to add and remove items, retrieve the items, and display the
*total items and cost.
*/
import java.util.*;

/**
*Constructs a new empty Cart object with an empty map of items and prices.
*/
public class Cart {
    private Map<Integer, Integer> items;
    private Map<Integer, Double> prices; // map of shoe IDs to prices
    
    public Cart() {
        items = new HashMap<>();
        prices = new HashMap<>();
    }
    
    /**
    * Adds the specified quantity of a given shoe to the cart. If the shoe is already in the cart,
    * its quantity is updated to include the specified quantity. Otherwise, a new entry is added
    * to the map of items with the specified quantity.
    * @param shoe the Shoe object to add to the cart
    * @param quantity the quantity of the Shoe object to add to the cart
    */
    public void addItem(Shoe shoe, int quantity) {
        if (items.containsKey(shoe.getId())) {
            items.put(shoe.getId(), items.get(shoe.getId()) + quantity);
        } else {
            items.put(shoe.getId(), quantity);
        }
        prices.put(shoe.getId(), shoe.getPrice());
    }

    /**
    * Adds the specified quantity of a given shoe to the cart. If the shoe is already in the cart,
    * its quantity is updated to include the specified quantity. Otherwise, a new entry is added
    * to the map of items with the specified quantity.
    * @param shoeId the ID of the shoe to add to the cart
    * @param quantity the quantity of the Shoe object to add to the cart
    * @param price the price of the Shoe object to add to the cart
    */
    public void wishAddItem(int shoeId, int quantity, double price) {
        if (items.containsKey(shoeId)) {
            items.put(shoeId, items.get(shoeId) + quantity);
        } else {
            items.put(shoeId, quantity);
            prices.put(shoeId, price);
        }
    }
    
    /**
    * Removes the shoe with the specified ID from the cart.
    * @param shoeId the ID of the shoe to remove from the cart
    */
    public void removeItem(int shoeId) {
        items.remove(shoeId);
        prices.remove(shoeId);
    }
    
    /**
    * Returns a map of the items in the cart, where the keys are the IDs of the shoes and the
    * values are the quantities of the shoes.
    * @return a map of the items in the cart
    */
    public Map<Integer, Integer> getItems() {
        return items;
    }
    
    /**
    * Returns the total number of items in the cart, calculated as the sum of the quantities of
    * all the shoes in the cart.
    * @return the total number of items in the cart
    */
    public int getTotalItems() {
        int totalItems = 0;
        for (int quantity : items.values()) {
            totalItems += quantity;
        }
        return totalItems;
    }
    
    /**
    * Returns the total cost of all the items in the cart, calculated as the sum of the products of
    * the quantities and prices of each shoe in the cart.
    * @return the total cost of all the items in the cart
    */
    public double getTotalCost() {
        double totalCost = 0.0;
        for (int shoeId : items.keySet()) {
            int quantity = items.get(shoeId);
            double price = prices.get(shoeId);
            totalCost += quantity * price;
        }
        return totalCost;
    }

    /**
    * Displays the items in the cart, along with their quantities and prices, and the total number
    * of items and cost of all the items in the cart.
    */
    public String display() {
        String output = "";
        if (items.isEmpty()) {
            output += "Your cart is empty.";
        } else {
            output += "Your cart contains:\n";
            for (int shoeId : items.keySet()) {
                int quantity = items.get(shoeId);
                double price = prices.get(shoeId);
                output += "- Shoe ID: " + shoeId + ", Quantity: " + quantity + ", Price: $" + price + "\n";
            }
            output += "Total items: " + getTotalItems() + "\n";
            output += "Total cost: $" + getTotalCost() + "\n";
        }
        return output;
    }




}
