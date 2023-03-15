/*
 * Caleb Eng
 * 3/15/2023
 * CPS-406
 * seller class:
 * holds all the information about a seller
 */

 import java.util.ArrayList;

public class Seller extends User{
 private ArrayList<Shoe> shoeList;
 private String description;
 
 /**
  *Construcor for the seller class
  *@param descriptionI
  *     seller description
  *Information for the parent class
  *@param idI
  *     seller id
  *@param nameI
  *     seller name
  *@param emailI
  *     seller email
  *@param passwordI
  *     seller account password
  *@see User
  */
 public Seller(int idI, String nameI, String emailI, String passwordI,String descriptionI){
    super(idI, nameI, emailI, passwordI, "Seller");
    this.description = descriptionI;
 }

 /**
  * adds a new shoe to the seller
  * @param newShoe
  *     new shoe for the seller
  */
 public void addShoe(Shoe newShoe){
    this.shoeList.add(newShoe);
 }

 public void removeShoe(Shoe remShoe){
    for(int i=0;i<this.shoeList.size();i++){
        if(this.shoeList.get(i).equals(remShoe)){
            this.shoeList.remove(remShoe);
            break;
        }
    }
 }
 /**
  * altering the description for the seller
  * @param descriptionI
  *     new description for the seller
  */
 public void setDescription(String descriptionI){
    this.description = descriptionI;
 }

 /**
  * adds new stock to the shoes in shoeList
  * @param newStock
  *     the number to increase the stock by
  * @see Shoe
  */
 public void addStock(int newStock){
    this.shoeList.addMoreStock(newStock);
 }

 /**
  * removes stock from the shoes in shoeList
  * @param number
  *     the number to decrease stock by
  * @see Shoe
  */
 public void removeStock(int number){
    this.shoeList.remStock(number);
 }

/**
 * sets the status of an order to shipped
 * @param order
 *      the order in which the seller wishes to ship
 * @see Orders
 */ 
 public void shipOrder(Orders order){
    order.setStatus("Shipped");
 }

/**
 * shows the product backlog of the seller
 * (unsure how this will be done)
 * 
 * @param something
 * @see placeHolder
 * 
 */
 public void viewProductBacklog(){

 }
}
