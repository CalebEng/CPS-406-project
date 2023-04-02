/*
The PastOrders class extends the Orders class and represents a collection of all past orders made by a user.
It keeps track of the total cost of all past orders and the number of orders made. It also provides methods for
viewing all past orders and the total cost of all inactive (delivered) orders.
*/

import java.util.ArrayList;

public class PastOrders extends Order {
    private double totalCost; // total cost of all past orders
    private int totalOrdersMade; // total number of past orders made
    private ArrayList<Order> pastOrderedItems; // list of all past orders

    /*
     * Constructor for the class
     */
    public PastOrders(Cart cart) {
        super(cart);
        this.totalCost = 0.0; // initialize total cost to 0
        this.totalOrdersMade = 0; // initialize total orders made to 0
        this.pastOrderedItems = new ArrayList<>(); // initialize the list of past ordered items to an empty ArrayList
    }

    /*
     * Returns a list of all inactive orders
     * 
     * @return
     *      An ArrayList of all inactive orders
     */
    public ArrayList<Order> viewTotalOrdersMade() {
        ArrayList<Order> inactiveOrders = new ArrayList<>(); // create a new ArrayList to hold inactive orders
        for (Order order : pastOrderedItems) { // loop through all past ordered items
            if (order.getStatus().equals("inactive")) { // if the order status is inactive
                inactiveOrders.add(order); // add the order to the list of inactive orders
            }
        }
        return inactiveOrders; // return the list of inactive orders
    }

    /*
     * Calculates the total cost of all inactive orders
     * 
     * @return
     *      The total cost of all inactive orders
     */
    public double viewTotalCost() {
        for (Order order : pastOrderedItems) { // loop through all past ordered items
            if (order.getStatus().equals("inactive")) { // if the order status is inactive
                totalCost += order.getTotal(); // add the total cost of the order to the total cost of all inactive orders
            }
        }
        return totalCost; // return the total cost of all inactive orders
    }

    /*
     * Adds a past order to the list and updates total cost and total orders made
     * 
     * @param order
     *      The order to be added to the list of past ordered items
     */
    public void addPastOrder(Order order) {
        pastOrderedItems.add(order); // add the order to the list of past ordered items
        totalOrdersMade++; // increment the total orders made
        totalCost += order.getTotal(); // add the total cost of the order to the total cost of all past orders
    }

    // Getters and setters for all attributes
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalOrdersMade() {
        return totalOrdersMade;
    }

    public void setTotalOrdersMade(int totalOrdersMade) {
        this.totalOrdersMade = totalOrdersMade;
    }

    public ArrayList<Order> getPastOrderedItems() {
        return pastOrderedItems;
    }
}
