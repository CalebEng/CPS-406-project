/*
 * Caleb Eng
 * 3/15/2023
 * CPS-406
 * Admin class class:
 * holds all the information about an Admin
 */
import java.util.ArrayList;
public class Admin extends User{

    /**
     * Constructor for the class
     * 
     * 
     * information for the parent class
     * @param idI
     *      admin id
     * @param nameI
     *      admin name
     * @param emailI
     *      admin email
     * @param passwordI
     *      admin password
     * @see User
     */
    public Admin(String nameI, String emailI, String passwordI) {
        super(nameI, emailI, passwordI, "Admin");
    }

    /**
     * adds shoe to the seller list
     * @param newShoe
     *      Shoe to be added
     * @param soldFrom
     *      the seller the shoe should be added to
     * @see Seller
     */
    public void addShoe(Shoe newShoe, Seller soldFrom){
        soldFrom.addShoe(newShoe);
    }

    /**
     * removes shoe from the seller list
     * @param remShoe
     *      The shoe to be removed
     * @param solFrom 
     *      The seller the shoe should be removed from
     * @returns the result of the seller class version of this function
     * @see Seller
     */
    public boolean removeShoe(Shoe remShoe,Seller soldFrom){
        return soldFrom.removeShoe(remShoe);
    }

    /**
     * shows the product backlog for a product
     * @param soldFrom
     *      the seller in which the product is being sold from
     * @returns the product backlog from the seller
     */
    public ArrayList<Orders> viewProductBacklog(Seller soldFrom){   
        return soldFrom.viewProductBacklog();

    }
    
}
