import java.io.*;
import java.util.*;

public class DatabaseTest {

    public static void main(String[] args) {
        Database db = new Database("C:/Users/mahad/Desktop/CPS406Assign");
        Shoe shoe = new Shoe(2, "Adidas Superstar", 80.00, 8, "Classic casual shoes", "Casual", "US 8", "White", "Adidas", "Jane Doe");
        User user = new User("Jane", "jane@example.com", "password", "shopper");


        System.out.println("Write Order Test: ");
        testWriteOrders(db);   
        System.out.println("Write Product Test: "); 
        testWriteProducts(db, shoe);
        System.out.println("Write User Test: ");
        testWriteUsers(db, user);
        System.out.println("Clear Orders Test: ");
        testClearOrders(db);
        System.out.println("Clear Products Test: ");
        testClearProducts(db, shoe);
        System.out.println("Clear Users Test: ");
        testClearUsers(db, user);
        System.out.println("LineToMap Test: ");
        testLineToMap(db);
        System.out.println("Delete Order Test: ");
        testDeleteOrder(db);
        System.out.println("Delete Product Test: ");
        testDeleteProduct(db, shoe);
        System.out.println("Delete User Test: ");
        testDeleteUser(db, user);
        System.out.println("Line to Map Test: ");
        
    }


    /**
     * Database Test 1:
     * Writes and checks if Order object is sucessfully written to the orders File
     * @param database
     * @see Database
     */
    public static void testWriteOrders(Database db) {
        try {
            Shoe shoe = new Shoe(2, "Nike Air Max", 120.00, 10, "Comfortable running shoes", "Running", "US 10", "Black", "Nike", "John Doe");
            Cart cart = new Cart();
            cart.addItem(shoe, 2);
            Order order = new Order(cart);
            order.setTotal(cart.getTotalCost());
            //order.viewOrder();
            db.writeOrders(order);
            System.out.println("Passed: Order successfully written to file!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Database Test 2:
     * Writes and checks if shoe object is sucessfully written to the products File
     * @param database
     * @param shoe
     * @see Database
     */
    public static void testWriteProducts(Database db, Shoe shoe) {
        try {
            db.writeProducts(shoe);
            System.out.println("Passed: Product successfully written to file!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Database Test 3:
     * Writes and checks if user object is sucessfully written to the users File
     * @param database
     * @param user
     * @see Database
     */
    public static void testWriteUsers(Database db, User user) {
        try {
            db.writeUsers(user);
            System.out.println("Passed: User successfully written to file!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Database Test 4:
     * Writes order objects to file and tests if order file gets cleared
     * @param database
     * @see Database
     */
    public static void testClearOrders(Database db) {
        try {
            Shoe shoe = new Shoe(2, "Nike Air Max", 120.00, 10, "Comfortable running shoes", "Running", "US 10", "Black", "Nike", "John Doe");
            Cart cart = new Cart();
            cart.addItem(shoe, 2);
            Order order = new Order(cart);
            order.setTotal(cart.getTotalCost());
            File file = new File("C:/Users/mahad/Desktop/CPS406Assign" + "/dbOrders.txt");

            db.writeOrders(order);
            db.clearOrders();
            if (file.length() == 0) {
                System.out.println("Passed: orders file has been cleared");
            }
            else {
                System.out.println("Failed: Error clearing orders file");
            }
        
        } catch (IOException e) {
            System.out.println("Error clearing orders file: " + e.getMessage());
        }
    }

    /**
     * Database Test 5:
     * Writes shoe objects to file and tests if shoe file gets cleared
     * @param database
     * @param shoe
     * @see Database
     */
    public static void testClearProducts(Database db, Shoe shoe) {
        try {
            File file = new File("C:/Users/mahad/Desktop/CPS406Assign" + "/dbOrders.txt");
            db.writeProducts(shoe);
            db.clearProducts();
            if (file.length() == 0) {
                System.out.println("Passed: products file has been cleared");
            }
            else {
                System.out.println("Failed: Error clearing products file");
            }
        } catch (IOException e) {
            System.out.println("Error clearing products file: " + e.getMessage());
        }
    }

    /**
     * Database Test 6:
     * Writes user objects to file and tests if user file gets cleared
     * @param database
     * @param user
     * @see Database
     */
    public static void testClearUsers(Database db, User user) {
        try {
            File file = new File("C:/Users/mahad/Desktop/CPS406Assign" + "/dbOrders.txt");
            db.writeUsers(user);
            db.clearUsers();
            if (file.length() == 0) {
                System.out.println("Passed: users file has been cleared");
            }
            else {
                System.out.println("Failed: Error clearing users file");
            }
        } catch (IOException e) {
            System.out.println( "Error clearing users file: " + e.getMessage());
        }
    }

    /**
     * Database Test 7:
     * Tests to see if line from a file can be sucessfully be converted to Map
     * @param database
     * @see Database
     */
    public static void testLineToMap(Database db) {
        String line = "ID:2|Name:Adidas Superstar|Price:80.0|Quantity:8|Description:Classic casual shoes|Category:Casual|Size:US 8|Color:White|Brand:Adidas|Seller:Jane Doe";
        Map<String, String> map = db.lineToMap(line);
        
        if (!map.get("ID").equals("2") ||
            !map.get("Name").equals("Adidas Superstar") ||
            !map.get("Price").equals("80.0") ||
            !map.get("Quantity").equals("8") ||
            !map.get("Description").equals("Classic casual shoes") ||
            !map.get("Category").equals("Casual") ||
            !map.get("Size").equals("US 8") ||
            !map.get("Color").equals("White") ||
            !map.get("Brand").equals("Adidas") ||
            !map.get("Seller").equals("Jane Doe")) {
            
            System.out.println("Failed: LineToMap");
        } else {
            System.out.println("Passed: LineToMap");
        }
    }

    /**
     * Database Test 8:
     * Tests to see if the correct array of shoes can be returned when given a keyword that matches those shoe objects attributes
     * @param database
     * @see Database
     */
    public static void testSearchProducts(Database db) {
        try {

            int test = 0;
            Shoe shoe1 = new Shoe(2, "Adidas Superstar", 80.00, 8, "Classic casual shoes", "Casual", "US 8", "White", "Adidas", "Jane Doe");
            Shoe shoe2 = new Shoe(3, "Nike Air Max 270", 150.00, 9, "Sporty and stylish sneakers", "Athletic", "US 9.5", "Black/White", "Nike", "John Smith");
            Shoe shoe3 = new Shoe(4, "Adidas Superstar", 80.00, 8, "Classic casual shoes", "Casual", "US 10", "Black", "Adidas", "Jane Doe");

            db.writeProducts(shoe1);
            db.writeProducts(shoe2);
            db.writeProducts(shoe3);

            ArrayList<Shoe> expectedArray = new ArrayList<Shoe>();
            expectedArray.add(shoe1);
            expectedArray.add(shoe3);

            ArrayList<Shoe> actualArray = db.searchProducts("Adidas Superstar");

            if (expectedArray.size() != actualArray.size()) {
                System.out.println("Failed");
            } else {
                for (int i = 0; i < expectedArray.size(); i++) {
                    if ((expectedArray.get(i).equals(actualArray.get(i)))) {
                        test = 1;
                        break;
                    }
                }
            }

            if (test == 0){
            System.out.println("Passed");
            }
            else
            {
                System.out.println("Error");
            }

            
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Database Test 9:
     * Writes order objects to file and tests if order object gets deleted from file
     * @param database
     * @see Database
     */
    public static void testDeleteOrder(Database db) {
        try {
            Shoe shoe = new Shoe(2, "Nike Air Max", 120.00, 10, "Comfortable running shoes", "Running", "US 10", "Black", "Nike", "John Doe");
            Cart cart = new Cart();
            cart.addItem(shoe, 2);
            Order order = new Order(cart);
            order.setTotal(cart.getTotalCost());
            File file = new File("C:/Users/mahad/Desktop/CPS406Assign" + "/dbOrders.txt");

            db.writeOrders(order);
    
            // Delete the product we just wrote
            db.deleteProduct(order.getOrderId());
    
            // Check if the product was deleted by trying to retrieve it
            String retrievedShoe = db.fromProducts(shoe.getId());
            if (retrievedShoe != null) {
                System.out.println("Error: order was not deleted");
            }
            else {
                System.out.println("Passed: order was deleted");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    /**
     * Database Test 10:
     * Writes shoe objects to file and tests if shoe object gets deleted from file
     * @param database
     * @param shoe
     * @see Database
     */
    public static void testDeleteProduct(Database db, Shoe shoe) {
        try {
            // Write a new product to the file
            db.writeProducts(shoe);
    
            // Delete the product we just wrote
            db.deleteProduct(shoe.getId());
    
            // Check if the product was deleted by trying to retrieve it
            String retrievedShoe = db.fromProducts(shoe.getId());
            if (retrievedShoe != null) {
                System.out.println("Error: product was not deleted");
            }
            else {
                System.out.println("Passed: product was deleted");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Database Test 11:
     * Writes user objects to file and tests if user object gets deleted from file
     * @param database
     * @param user
     * @see Database
     */
    public static void testDeleteUser(Database db, User user) {
        try {
            // Write a new product to the file
            db.writeUsers(user);
    
            // Delete the product we just wrote
            db.deleteProduct(user.getId());
    
            // Check if the product was deleted by trying to retrieve it
            String retrievedShoe = db.fromProducts(user.getId());
            if (retrievedShoe != null) {
                System.out.println("Error: user was not deleted");
            }
            else {
                System.out.println("Passed: user was deleted");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }



}