import java.io.IOException;

/*
A simple playground for testing the GUI. Please note that the GUI/frontend has not yet been implemented with the backend code.
This file is solely for testing the functionality of the GUI itself, to make sure it functions.
*/

public class TestGUI {
    public static void main (String[]args){

        //Database db = new Database("./database");
        //Shopper test = new Shopper("test1", "wall street", "password", "test1@email.com");
        //Seller test2 = new Seller("costco", "costco@email.com", "password_2", "Whole Sale Retailer");
        /*
        try {
            db.writeUsers(test);
            db.writeUsers(test2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
        new GUI();
    }
}
