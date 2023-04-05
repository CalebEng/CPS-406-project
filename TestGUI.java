import java.io.IOException;

/*
A simple playground for testing the GUI. This file is solely for testing the functionality of the GUI itself, to make sure it functions.

IMPORTANT NOTE: PLEASE NOTE THAT THE GUI'S FUNCTIONALITY MAY NOT BE REPRESENTATIVE OF THE OTHER CLASSES IT USES, PLEASE SEE THE RESPECTIVE TEST FILES FOR EACH CLASS.
*/

public class TestGUI {
    public static void main (String[]args){    
        /*
        Database db = new Database("./database");    
        Shoe shoe = new Shoe(1, "Reebok Shoe", 80.00, 10, "Not Jordan's, but good enough.", "Athletic", "9.5", "White", "Reebok", "John");
        Shoe shoe2 = new Shoe(3, "Nike Air Force 1", 150.00, 10, "Not Jordan's, but good enough.", "Athletic", "9.5", "White", "Nike", "John");
        shoe.addReview(new Review(5.0, "these are seriously some nice shoes"));

        Shopper testShopper = new Shopper("Joe Mama", "Gold Street", "jojo", "joe@mama.com");
        testShopper.addToCart(shoe, 2);
        testShopper.addToCart(shoe2, 3);

        try {
            db.writeUsers(testShopper);
            String test = db.fromUsers(testShopper.getId());
            System.out.println(db.lineToMap(test));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */

        // since the GUI does not have any formal test cases, please refer to the document. 
        new GUI();
    }
}
