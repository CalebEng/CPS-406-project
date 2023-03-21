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

   private ArrayList<Orders> orderlist;
   private ArrayList<Orders> shippedOrdersList;
   
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

   /**
    * removes a shoe from the seller list
    * @param remShoe
    *       Shoe to be removed
    */
   public void removeShoe(Shoe remShoe){
      for(int i=0;i<this.shoeList.size();i++){
         if(this.shoeList.get(i).equals(remShoe)){
               this.shoeList.remove(i);
               return;
         }
      }
   }

   /**
    * returns a shoe for the user to view later
    * @param toView
    *    the shoe to look for
    * @return shoe to be viewed
    * @see Shoe
    *unsure how this will work
    */
   public Shoe viewShoe(Shoe toView){
         return ;
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
    * getter for the seller description
    * @return description of the seller
    */
   public String getDescription(){
      return this.description;
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
    * @param oid
    *      the order id in which the seller wishes to ship
    * @see Orders
    */ 
   public void shipOrder(int oid){
      for(int i =0;i<orderlist.size();i++){
         if(this.orderlist.get(i).getOID()== oid){
            this.orderlist.get(i).setStatus("Shipped");
            this.shippedOrdersList.add(this.orderlist.get(i));
            this.orderlist.remove(i)
            return;
         }
      }
   }

   /**
    * sends back the orderlist that has not been shipped
    * @param soldFrom
    *       the seller in which the prodcut is sold from
    * @returns orderlist
    */
   public ArrayList<Orders> viewProductBacklog(){
      return this.orderlist;
   }


   /**
    * To string method for the seller class
    * @returns seller information
    */
   public String toString(){
      return "Acc name: "+this.getName()+"\nAcc ID: "+this.getId()+"\nAcc email: "+this.getEmail()+"\nAcc password: "+this.getPassword()+"\nAcc type: "+this.getType()+"\nDescription: "+this.getDescription()
      +"\nShoe list: "+this.shoeList+"\nOrders list: "+this.orderlist+"\nShipped Orders: " +this.shippedOrdersList ;
   }
}
