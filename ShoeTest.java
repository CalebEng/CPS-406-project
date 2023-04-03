/*
 * Yong Tan
 * cps 406
 * testing shoe class
 */

public class ShoeTest {
    public static void main(String[] args) {
        System.out.println("--- A result of 'true' means the test passed. --- ");
        System.out.println("testShoeConstructor: " + testShoeConstructor()); // Test constructor
        System.out.println("testAddStock: " + testAddStock()); // Test add stock
        System.out.println("testRemoveStock: " + testRemoveStock()); // Test remove stock
    }
    
    /**
     * ShoeTest 1: Shoe Constructor
     * @param name
     * @param price
     * @param stock count
     * @param description
     * @param type
     * @param size
     * @param colour
     * @param brand
     * @param added by:
     * @param review
     */
    public static boolean testShoeConstructor() {
        Shoe shoe = new Shoe(1, "Nike Air Force 1", 150.00, 10, "Not Jordan's, but good enough.", "Athletic", "9.5", "White", "Nike", "John");
        return shoe.getName().equals("Nike Air Force 1") &&
                shoe.getPrice() == 150.00 &&
                shoe.getStockCount() == 10 &&
                shoe.getDescription().equals("Not Jordan's, but good enough.") &&
                shoe.getType().equals("Athletic") &&
                shoe.getSize().equals("9.5") &&
                shoe.getColour().equals("White") &&
                shoe.getBrand().equals("Nike") &&
                shoe.getAddedBy().equals("John") &&
                shoe.getReviews().isEmpty();
    }

    /**
     * ShoeTest 2: adding stock
     */
    public static boolean testAddStock() {
        Shoe shoe = new Shoe(1, "Nike Air Force 1", 150.00, 10, "Not Jordan's, but good enough.", "Athletic", "9.5", "White", "Nike", "John");
        shoe.addStock(5);
        return shoe.getStockCount() == 15;
    }

    /**
     * ShoeTest 3: removing stock
        normally
        removing more than remaining stock
        removing nothing
     */
    public static boolean testRemoveStock() {
        Shoe shoe = new Shoe(1, "Nike Air Force 1", 150.00, 10, "Not Jordan's, but good enough.", "Athletic", "9.5", "White", "Nike", "John");
        shoe.removeStock(5); // remove 5
        if (shoe.getStockCount() != 5) { // should have 5 left
            return false;
        }
        try {
            shoe.removeStock(6); // try to remove 6 when only 5 left
            return false;
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().equals("Not enough stock available")) { // check if correct exception was thrown
                return false;
            }
        }
        try {
            shoe.removeStock(0); // try to remove 0
            return false;
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().equals("Nothing is removed")) { // check if correct exception was thrown
                return false;
            }
        }
        return true;
    }
}
