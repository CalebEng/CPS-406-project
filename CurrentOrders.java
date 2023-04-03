/*
The CurrentOrders class extends the Orders class and represents the current active orders placed by a user.
This class contains an ArrayList of Order objects representing the items in the user's current order,
as well as variables for the total amount spent and total number of orders placed.
The class allows the user to add and remove items from their current order, and view the total amount spent
and number of orders placed.
*/

import java.util.ArrayList;

public class CurrentOrders extends Order {
    private double totalSpent; // total amount spent on current orders
    private int totalOrders; // total number of current orders
    private ArrayList<Order> currentOrderedItems; // list of all current orders

    /*
     * Constructor for the class
     */
    public CurrentOrders(Cart cart) {
        super(cart); // pass the cart object to the Order constructor using super
        this.totalSpent = 0.0; // initialize total amount spent to 0
        this.totalOrders = 0; // initialize total number of orders to 0
        this.currentOrderedItems = new ArrayList<>(); // initialize the list of current orders to an empty ArrayList
    }

    /**
     * Returns the number of active orders in the current order list.
     * 
     * @return The number of active orders in the current order list.
     */
    public ArrayList<Order> viewTotalOrders() {
        ArrayList<Order> activeOrders = new ArrayList<>(); // create a new ArrayList to hold active orders
        for (Order order : currentOrderedItems) { // loop through all current ordered items
            if (order.getStatus().equals("active")) { // if the order status is active
                activeOrders.add(order); // add the order to the list of active orders
            }
        }
        return activeOrders; // return the list of active orders
    }

    
    /*
     * Returns the total amount spent on active orders in the current order list
     * 
     * @return
     *      The total amount spent on active orders in the current order list
     */
    public double viewTotalSpent() {
        totalSpent = 0; // reset totalCost to 0
        for (Order order : currentOrderedItems) { // loop through all current ordered items
            if (order.getStatus().equals("active")) { // if the order status is active
                totalSpent += order.getTotal(); // add the total cost of the order to the total amount spent on current orders
            }
        }
        return totalSpent; // return the total amount spent on current orders
    }

    /*
     * Adds an Order object to the current order list, and updates the total amount spent and number of orders
     * 
     * @param order
     *      The order to be added to the list of current orders
     */
    public void addItem(Order order) {
        currentOrderedItems.add(order); // add the order to the list of current orders
        totalOrders++; // increment the total number of orders
        totalSpent += order.getTotal(); // add the total cost of the order to the total amount spent on current orders
    }

    /*
     * Removes an Order object from the current order list, and updates the total amount spent and number of orders
     * 
     * @param order
     *      The order to be removed from the list of current orders
     */
    public void removeItem(Order order) {
        currentOrderedItems.remove(order); // remove the order from the list of current orders
        totalOrders--; // decrement the total number of orders
        totalSpent -= order.getTotal(); // subtract the total cost of the order from the total amount spent on current orders
    }

    // Getters and setters for all attributes

    /**
     * Sets the list of current orders to the specified list of orders and updates the total number of active orders.
     *
     * @param currentOrders the list of current orders to be set
     */
    public void setCurrentOrders(ArrayList<Order> currentOrders) {
        this.currentOrderedItems = currentOrders;
        totalOrders = currentOrders.size(); // set totalActiveOrders to the size of the currentOrders list
    }
    
    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
	}
	
	public ArrayList<Order> getCurrentOrderedItems() {
		return currentOrderedItems;
	}




}	
