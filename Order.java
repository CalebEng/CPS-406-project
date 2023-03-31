/*
The Order class represents an order placed by a user. Each order contains a reference to a cart object,
which contains the items that the user has ordered. When an order is placed, a new order object is created,
and the cart object is passed to its constructor. The order object calculates the total cost of the items in
the cart and sets its status to active. Once the order has been delivered, its status is changed to inactive.
*/

import java.util.Date;
import java.util.Random;

public class Order {
    private int orderId; // unique identifier for the order
    private String status; // current status of the order (active or inactive)
    private double total; // total cost of all the items in the order
    private Date orderDate; // date and time when the order was placed
    private Date estimatedDate; // estimated date and time when the order will be delivered
    private Cart cart; // reference to the cart containing the items in the order

    /*
     * Constructor for the class
     * 
     * @param cart
     *      The cart containing the items in the order
     */
    public Order(Cart cart) {
        this.orderId = generateOrderId(); // generate a unique order ID using the generateOrderId() method
        this.status = "active"; // set the status to active
        this.cart = cart; // set the cart to the given cart object
        this.total = cart.getTotalCost(); // calculate the total cost of the items in the cart
        this.orderDate = new Date(); // set the order date to the current date and time
        this.estimatedDate = new Date(orderDate.getTime() + (7 * 24 * 60 * 60 * 1000)); // set the estimated delivery date to one week after the order date
    }

    /*
     * Displays the order details, including the cart items, total cost, and order and estimated delivery dates
     */
    public void viewOrder() {
        System.out.println("Order #" + orderId);
        System.out.println("Status: " + status);
        System.out.println("Order Date: " + orderDate.toString());
        System.out.println("Estimated Delivery Date: " + estimatedDate.toString());
        cart.display(); // call the viewCart() method of the cart object to display the cart items
        System.out.println("Total Cost: $" + total);
    }

    /*
     * Checks if the estimated delivery date has passed and changes the order status to inactive if it has
     */
    public void checkDeliveryStatus() {
        Date currentDate = new Date(); // get the current date and time
        if (currentDate.after(estimatedDate)) { // if the current date and time is after the estimated delivery date
            status = "inactive"; // change the status to inactive
        }
    }

    /*
     * Generates a random 4-digit number to use as the order ID
     * 
     * @return
     *      The randomly generated order ID
     */
    private int generateOrderId() {
        Random rand = new Random(); // create a new Random object
        int orderId = rand.nextInt(9000) + 1000; // generate a 4-digit random number between 1000 and 9999
        return orderId;
    }

    // Getters and setters for all attributes
    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getEstimatedDate() {
        return estimatedDate;
    }

    public Cart getCart() {
        return cart;
    }

    /*
     * Returns a formatted string representing the order for storing in a database
     * 
     * @return
    */
    public String toDatabase() {
        return "OrderID:" + orderId + "|Status:" + status + "|Total:" + total + "|OrderDate:" + orderDate.getTime() + "|EstimatedDate:" + estimatedDate.getTime() + "\n";
    }
}

