/*
 * Ismail Gangi
 * cps 406
 * testing order classes
 */

import java.util.*;
public class OrderTest {
public static void main(String[] args) {
    testViewOrder();
    testCheckDeliveryStatus();
    testGenerateOrderId();
    testViewTotalOrdersMade();
    testViewTotalCost();
    testAddPastOrders();
    testViewTotalOrders();
    testViewTotalSpent();
}

/**
 * Tests the viewOrder method of the Order class.
 * It creates a cart with an item and creates an order from the cart.
 * Then, it compares the expected output of the viewOrder method to the actual output.
 * If they are equal, the test passes; otherwise, it fails.
 */
public static void testViewOrder() {
    // Create a cart with an item and an order from the cart
    Cart cart = new Cart();
    Shoe shoe = new Shoe(1, "Nike Air Max", 120.0, 5, "The Nike Air Max is a popular shoe model.", "Running", "US 10", "Red", "Nike", "John");
    cart.addItem(shoe, 2);
    Order order = new Order(cart);

    // Define the expected output of the viewOrder method
    String expectedOutput = "Order #" + order.getOrderId() + "\nStatus: " + order.getStatus() + "\nOrder Date: " + order.getOrderDate().toString() + "\nEstimated Delivery Date: " + order.getEstimatedDate().toString() + "\nYour cart contains:\n- Shoe ID: 1, Quantity: 2, Price: $120.0\nTotal items: 2\nTotal cost: $240.0\n";

    // Get the actual output of the viewOrder method
    String actualOutput = order.viewOrder();

    // Compare the expected and actual outputs
    if (expectedOutput.equals(actualOutput)) {
        System.out.println("testViewOrder Passed");
    } else {
        System.out.println("testViewOrder Failed");
    }
}


/**
 * Tests the checkDeliveryStatus method of the Order class.
 * It creates a cart with an item and creates an order from the cart.
 * Then, it sets the status of the order to "inactive" and checks the delivery status.
 * If the expected status and actual status are equal, the test passes; otherwise, it fails.
 */
public static void testCheckDeliveryStatus() {
    // Create a cart with an item and an order from the cart
    Cart cart = new Cart();
    Shoe shoe = new Shoe(1, "Nike Air Max", 120.0, 5, "The Nike Air Max is a popular shoe model.", "Running", "US 10", "Red", "Nike", "John");
    cart.addItem(shoe, 2);
    Order order = new Order(cart);

    // Set the status of the order to "inactive" and check the delivery status
    order.setStatus("inactive");
    order.checkDeliveryStatus();
    String expectedOutput = "inactive";
    String actualOutput = order.getStatus();

    // Compare the expected and actual statuses
    if (expectedOutput.equals(actualOutput)) {
        System.out.println("testCheckDeliveryStatus Passed");
    } else {
        System.out.println("testCheckDeliveryStatus Failed");
    }
}


/**
 * Tests the generateOrderId method of the Order class.
 * It creates a cart with an item and creates an order from the cart.
 * Then, it generates an order ID and compares the expected length of the ID to the actual length.
 * If they are equal, the test passes; otherwise, it fails.
 */
public static void testGenerateOrderId() {
    // Create a cart with an item and an order from the cart
    Cart cart = new Cart();
    Shoe shoe = new Shoe(1, "Nike Air Max", 120.0, 5, "The Nike Air Max is a popular shoe model.", "Running", "US 10", "Red", "Nike", "John");
    cart.addItem(shoe, 2);
    Order order = new Order(cart);
    
    // Generate an order ID and compare the expected length to the actual length
    int expectedLength = 4;
    order.generateOrderId();
    int actualLength = String.valueOf(order.getOrderId()).length();
    
    // Compare the expected and actual ID lengths
    if (expectedLength == actualLength) {
        System.out.println("testGenerateOrderId Passed");
    } else {
        System.out.println("testGenerateOrderId Failed");
    }
}

    
 /**
 * Tests the viewTotalOrdersMade method of the PastOrders class.
 * It creates a list of past orders and adds them to a PastOrders object.
 * Then, it compares the expected number of inactive orders to the actual number of inactive orders.
 * If they are equal, the test passes; otherwise, it fails.
*/
public static void testViewTotalOrdersMade() {
    // Create a list of past orders and add them to a PastOrders object
    ArrayList<Order> pastOrders = new ArrayList<>();
    Cart cart1 = new Cart();
    Shoe shoe1 = new Shoe(1, "Nike Air Max", 120.0, 5, "The Nike Air Max is a popular shoe model.", "Running", "US 10", "Red", "Nike", "John");
    cart1.addItem(shoe1, 2);
    pastOrders.add(new Order(cart1));
    Cart cart2 = new Cart();
    Shoe shoe2 = new Shoe(2, "Adidas Ultraboost", 150.0, 7, "The Adidas Ultraboost is a comfortable shoe for running.", "Running", "US 9", "Black", "Adidas", "Sarah");
    cart2.addItem(shoe2, 1);
    pastOrders.add(new Order(cart2));
    PastOrders pastOrdersObj = new PastOrders(new Cart());
    pastOrdersObj.setPastOrders(pastOrders);
    
    // Compare the expected and actual number of inactive orders
    int expectedTotal = 0;
    ArrayList<Order> inactiveOrders = pastOrdersObj.viewTotalOrdersMade();
    for (Order order : inactiveOrders) {
        if (order.getStatus().equals("inactive")) {
            expectedTotal++;
        }
    }
    int actualTotal = inactiveOrders.size();
    if (expectedTotal == actualTotal) {
        System.out.println("testViewTotalOrdersMade Passed");
    } else {
        System.out.println("testViewTotalOrdersMade Failed");
    }
}


/**
 * Tests the viewTotalCost method of the PastOrders class.
 * It creates an order with a cart containing one item and marks it as inactive.
 * Then, it adds the order to a list of past orders and creates a PastOrders object with the list.
 * Finally, it compares the expected total cost to the actual total cost returned by the viewTotalCost method.
 * If they are equal, the test passes; otherwise, it fails.
 */
public static void testViewTotalCost() {
    // Create a cart with an item and create an inactive order from the cart
    Cart cart = new Cart();
    Shoe shoe = new Shoe(1, "Nike Air Max", 120.0, 5, "The Nike Air Max is a popular shoe model.", "Running", "US 10", "Red", "Nike", "John");
    cart.addItem(shoe, 2);
    Order order = new Order(cart);
    order.markAsInactive(); // Mark the order as inactive

    // Add the inactive order to a list of past orders and create a PastOrders object with the list
    PastOrders pastOrders = new PastOrders(cart);
    pastOrders.addPastOrder(order);

    // Compare the expected total cost to the actual total cost returned by the viewTotalCost method
    double expectedTotalCost = 240.0;
    double actualTotalCost = pastOrders.viewTotalCost();
    if (expectedTotalCost == actualTotalCost) {
        System.out.println("testViewTotalCost Passed");
    } else {
        System.out.println("testViewTotalCost Failed");
    }
}




/**
 * Tests the addPastOrders method of the PastOrders class.
 * It creates a cart with an item and creates an order from the cart.
 * Then, it creates a PastOrders object and adds the order to it.
 * Finally, it compares the expected size of the past ordered items list to the actual size.
 * If they are equal, the test passes; otherwise, it fails.
 */
public static void testAddPastOrders() {
    // Create a cart with an item and an order from the cart
    Cart cart = new Cart();
    Shoe shoe = new Shoe(1, "Nike Air Max", 120.0, 5, "The Nike Air Max is a popular shoe model.", "Running", "US 10", "Red", "Nike", "John");
    cart.addItem(shoe, 2);
    Order order = new Order(cart);

    // Create a PastOrders object and add the order to it
    PastOrders pastOrders = new PastOrders(cart);
    pastOrders.addPastOrder(order);

    // Compare the expected and actual sizes of the past ordered items list
    int expectedSize = 1;
    int actualSize = pastOrders.getPastOrderedItems().size();

    // Determine whether the test passed or failed
    if (expectedSize == actualSize) {
        System.out.println("testAddPastOrders Passed");
    } else {
        System.out.println("testAddPastOrders Failed");
    }
}


/**
 * Tests the viewTotalOrders method of the CurrentOrders class.
 * It creates a CurrentOrders object and adds the current "active" orders to it.
 * Then, it compares the expected list of current orders to the actual list.
 * If they are equal, the test passes; otherwise, it fails.
 */
public static void testViewTotalOrders() {
    // Create a cart and a CurrentOrders object
    Cart cart = new Cart();
    CurrentOrders currentOrders = new CurrentOrders(cart);
    
    // Create first order
    Cart cart1 = new Cart();
    Shoe shoe1 = new Shoe(1, "Nike Air Max", 120.0, 5, "The Nike Air Max is a popular shoe model.", "Running", "US 10", "Red", "Nike", "John");
    cart1.addItem(shoe1, 2);
    Order order1 = new Order(cart1);
    currentOrders.addItem(order1);
    
    // Create second order
    Cart cart2 = new Cart();
    Shoe shoe2 = new Shoe(2, "Adidas Ultraboost", 90.0, 3, "The Adidas Ultraboost is a comfortable shoe for running.", "Running", "US 9", "Blue", "Adidas", "Jane");
    cart2.addItem(shoe2, 2);
    Order order2 = new Order(cart2);
    currentOrders.addItem(order2);
    
    // Create third order
    Cart cart3 = new Cart();
    Shoe shoe3 = new Shoe(3, "New Balance Fresh Foam", 135.0, 2, "The New Balance Fresh Foam is a durable shoe for long distance running.", "Running", "US 11", "Gray", "New Balance", "Joe");
    cart3.addItem(shoe3, 2);
    Order order3 = new Order(cart3);
    currentOrders.addItem(order3);
    
    // Create expected list of current orders
    ArrayList<Order> expectedCurrentOrders = new ArrayList<>();
    expectedCurrentOrders.add(order1);
    expectedCurrentOrders.add(order2);
    expectedCurrentOrders.add(order3);
    
    // Get actual list of current orders
    ArrayList<Order> actualCurrentOrders = currentOrders.viewTotalOrders();
    
    // Compare expected and actual lists
    boolean isEqual = expectedCurrentOrders.equals(actualCurrentOrders);
    System.out.println(expectedCurrentOrders);
    System.out.println(actualCurrentOrders);
    if (isEqual) {
        System.out.println("testViewTotalOrders Passed");
    } else {
        System.out.println("testViewTotalOrders Failed");
    }
}


/**
 * Tests the viewTotalSpent method of the CurrentOrders class.
 * It creates a list of current "active" orders with items, adds them to a CurrentOrders object,
 * and checks the total amount spent by the customer.
 * If the expected total and actual total are equal, the test passes; otherwise, it fails.
 */
public static void testViewTotalSpent() {
    // Create a list of current orders with items and add them to a CurrentOrders object
    ArrayList<Order> currentOrders = new ArrayList<>();
    Cart cart = new Cart();
    Shoe shoe = new Shoe(1, "Nike Air Max", 120.0, 5, "The Nike Air Max is a popular shoe model.", "Running", "US 10", "Red", "Nike", "John");
    cart.addItem(shoe, 2);
    Order order1 = new Order(cart);
    Order order2 = new Order(cart);
    currentOrders.add(order1);
    currentOrders.add(order2);
    CurrentOrders currentOrdersObj = new CurrentOrders(cart);
    currentOrdersObj.setCurrentOrders(currentOrders);

    // Check the total amount spent by the customer
    double expectedTotal = 480.0;
    double actualTotal = currentOrdersObj.viewTotalSpent();

    // Compare the expected and actual total amounts spent
    if (expectedTotal == actualTotal) {
        System.out.println("testViewTotalSpent Passed");
    } else {
        System.out.println("testViewTotalSpent Failed");
    }
}

}

