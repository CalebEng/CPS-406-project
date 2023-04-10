/*
 * Caleb Eng
 * 3/15/2023
 * CPS-406
 * User class which holds all of the information about the user
 *  ^ Shopper
 *  ^ Seller
 *  ^ Admin
 */
import java.util.Random;

public class User {
    Random random = new Random();
    private int id;
    private boolean hasAccount;
    private String name;
    private String email;
    private String password;
    private String type;

    private boolean loggedIn;


    /**
     * Class constructor
     * @param idI
     *      users id
     * @param nameI
     *      users name
     * @param emailI
     *      user email
     * @param passwordI
     *      user account password
     * @param typeI
     *      user type?
     * @see Seller
     * @see Shopper
     * @see Admin
     */
    public User(String nameI, String emailI, String passwordI, String typeI){
        this.id = random.nextInt(100);
        this.email = emailI;
        this.password = passwordI;
        this.type = typeI;
        this.loggedIn = false;
        this.name = nameI;
    }


    //getters:
    public int getId(){
        return id;
    }
    public boolean getHasAccount(){
        return hasAccount;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getType(){
        return type;
    }
    public String getName(){
        return name;
    }

    //setters:
    public void setEmail(String emailI){
        this.email = emailI;
    }
    public void setPassword(String passwordI){
        this.password = passwordI;
    }
    public void setID(int idI){
        this.id = idI;
    }
    public void setHasAccount(boolean tf){
        this.hasAccount =tf;
    }
    public void setName(String newName){
        this.name = newName;
    }

    //other class methods:
    /**
     * method for the user to login to their account
     * @param idI
     *      id of the account
     * @param passwordI
     *      password for the account associated with said id
     * @returns true if the user id and password match and false otherwise
     */
    public boolean login(int idI,String passwordI){
        if((idI == this.id)&&(passwordI == this.password)){
            this.loggedIn = true;
            return true;
        }
        else return false;
    }

    /**
     * method for user to logout of thier account
     * sets the loggedIn varaible to false
     */
    public void logout(){
        this.loggedIn = false;
    }

    /**
     * method to update the profile of the user
     * @param
     */
    public void updateProile(){

    }

    /**
     * method to check if someone is logged in
     * @returns true if the use is logged in and false otherwise
     */
    public boolean isLoggedIn(){
        return this.loggedIn;
    }

    /**
     * Grabs all the information about the user and returns it out to the calling class
     * @return String containing informations about the user class for use in other classes
     * @see Database
     */
    public String toDatabase(){
        return "AccID:" + this.getId() + "|AccName:"+this.getName() +"|AccEmail:"+this.getEmail()+"|AccPassword:"+this.getPassword()+"|AccType:"+this.getType() + "\n";
    }


    /**
     * tostring for testing
     * @returns the information about the user class
     */
    public String toString(){
        return "Acc name: "+this.getName()+"\nAcc ID: "+this.getId()+"\nAcc email: "+this.getEmail()+"\nAcc password: "+this.getPassword()+"\nAcc type: "+this.getType();
    }

}
