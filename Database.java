import java.io.*;
import java.util.*;

/*
 * Database class:
 * writes all the information about Orders, Products, and Users to respective files
 * gets information on Order, Product, and Users from respective files
 * deletes information on Order, Product, and User from respective files
 */

 public class Database {
    private String basePath;

    /**
     * Constructor for Database class
     * @param basePath the path where files are stored
     */
    public Database(String basePath) {
        this.basePath = basePath;
    }


    /**
     * Writes order information to the Order File
     * @param order the Order object to be written to file
     * @throws IOException when there is an error writing to file
     */
    public void writeOrders(Order order) throws IOException {
        File file = new File(basePath + "/dbOrders.txt");
        int inFile = 0;
        Scanner scanner = new Scanner(file);
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
    
            String[] parts = line.split("\\|");
            int lineId = Integer.parseInt(parts[0].substring(8));
    
            if (lineId == order.getOrderId()) {
                inFile = 1;
            }
        }
    

        if (inFile == 0) {
            writeToFile(basePath + "/dbOrders.txt", order.toDatabase());
        }
        else {
            System.out.println("Couldn't add order to database, order already in database");
        }
        scanner.close();


        
    }

    /**
     * Writes product information to the Product File
     * @param shoe the Shoe object to be written to file
     * @throws IOException when there is an error writing to file
     */
    public void writeProducts(Shoe shoe) throws IOException {
        File file = new File(basePath + "/dbProducts.txt");
        int inFile = 0;
        Scanner scanner = new Scanner(file);
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
    
            String[] parts = line.split("\\|");
            int lineId = Integer.parseInt(parts[0].substring(3));
    
            if (shoe.getId() == lineId) {
                inFile = 1;
            }

        }
    
        if (inFile == 0) {
            writeToFile(basePath + "/dbProducts.txt", shoe.toDatabase());
        }
        else {
            System.out.println("Couldn't add product to database, product already in database");
        }
    
        scanner.close();
        
    }

    /**
     * Writes user information to the User File
     * @param user the User object to be written to file
     * @throws IOException when there is an error writing to file
     */
    public void writeUsers(User user) throws IOException {
        File file = new File(basePath + "/dbUsers.txt");
        int inFile = 0;
        Scanner scanner = new Scanner(file);
    
        while (scanner.hasNextLine()) {
            
            String line = scanner.nextLine();
    
            String[] parts = line.split("\\|");
            int lineId = Integer.parseInt(parts[0].substring(6));
            String email = (parts[2].substring(9));

            if (user.getId() == lineId || email.equals(user.getEmail())) {
                inFile = 1;
            }

   
        }

        if (inFile == 0) {
            writeToFile(basePath + "/dbUsers.txt", user.toDatabase());
        }
        else {
            System.out.println("Couldn't add user to database, user already in database");
        }
    
        scanner.close();

    }
    

    /**
     * Clears the Order File
     * @throws IOException when there is an error writing to file
     */
    public void clearOrders() throws IOException {
        clearFile(basePath + "/dbOrders.txt");
    }

    /**
     * Clears the Product File
     * @throws IOException when there is an error writing to file
     */
    public void clearProducts() throws IOException {
        clearFile(basePath + "/dbProducts.txt");
    }

    /**
     * Clears the User File
     * @throws IOException when there is an error writing to file
     */
    public void clearUsers() throws IOException {
        clearFile(basePath + "/dbUsers.txt");
    }

    /**
     * Writes data to file
     * @param fileName the name of the file to be written to
     * @param data the data to be written to file
     * @throws IOException when there is an error writing to file
     */
    private void writeToFile(String fileName, String data) throws IOException {
        FileWriter writer = new FileWriter(fileName, true);
        writer.write(data);
        writer.close();
    }

    /**
     * Clears the contents of a file
     * @param fileName the name of the file to be cleared
     * @throws IOException when there is an error writing to file
     */    
    private void clearFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        writer.write("");
        writer.close();
    }


    /**
     * Converts information from the Product, User or Order file to a Map information can be called through key value pairs
     * @param line the line from the file to be converted
     * @return a Map containing the information from Product, User or Order
     */
    public Map<String, String> lineToMap(String line) {
        Map<String, String> map = new HashMap<>();
        String[] sections = line.split("\\|");
    
        for (String section : sections) {
            String[] parts = section.split(":");

            if (parts.length == 2){
                map.put(parts[0], parts[1]);
            } else{
                map.put(parts[0], "");
            }
        }
    
        return map;
    }

   /**
    * Retrieves a line from the dbProducts.txt file based on the provided ID
    * @param id the ID of the product to retrieve
    * @return a String representing the line from the file with the matching ID, or null if not found
    * @throws FileNotFoundException if the dbProducts.txt file cannot be found
    */
    public String fromProducts(int id) throws FileNotFoundException {
        File file = new File(basePath + "/dbProducts.txt");
        Scanner scanner = new Scanner(file);
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
    
            String[] parts = line.split("\\|");
            int lineId = Integer.parseInt(parts[0].substring(3));
    
            if (lineId == id) {
                scanner.close();
                return line;
            }
        }
    
        scanner.close();
        return null;
    }

 
   /**
    * Deletes the line with the provided ID from the dbProducts.txt file
    * @param id the ID of the product to delete
    * @throws IOException if an I/O error occurs while reading or writing to the file
    */
    public void deleteProduct(int id) throws IOException {
        File file = new File(basePath + "/dbProducts.txt");
        File tempFile = new File("tempFile.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
    
        while ((currentLine = reader.readLine()) != null) {
            String[] parts = currentLine.split("\\|");
            int lineId = Integer.parseInt(parts[0].substring(3));
            
            if (lineId != id) {
                writer.write(currentLine + "\n");
            }
        }
    
        writer.close();
        reader.close();
        file.delete();
        tempFile.renameTo(file);
    }


    /**
    * Retrieves a line from the dbUsers.txt file based on the provided ID
    * @param id the ID of the user to retrieve
    * @return a String representing the line from the file with the matching ID, or null if not found
    * @throws FileNotFoundException if the dbUsers.txt file cannot be found
    */
    public String fromUsers(int id) throws FileNotFoundException {
        File file = new File(basePath + "/dbUsers.txt");
        Scanner scanner = new Scanner(file);
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
    
            String[] parts = line.split("\\|");
            int lineId = Integer.parseInt(parts[0].substring(6));
    
            if (lineId == id) {
                scanner.close();
                return line;
            }
        }
    
        scanner.close();
        return null;
    }
    
   /**
    * Deletes the line with the provided ID from the dbUsers.txt file
    * @param id the ID of the user to delete
    * @throws IOException if an I/O error occurs while reading or writing to the file
    */
    public void deleteUser(int id) throws IOException {
        File file = new File(basePath + "/dbUsers.txt");
        File tempFile = new File("tempFile.txt");
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
    
        while ((currentLine = reader.readLine()) != null) {
            String[] parts = currentLine.split("\\|");
            int lineId = Integer.parseInt(parts[0].substring(6));

            if (lineId != id) {
                writer.write(currentLine + "\n");
            }
        }

        writer.close();
        reader.close();

        file.delete();
        tempFile.renameTo(file);
    }

   /**
    * Retrieves a line from the dbOrders.txt file based on the provided ID
    * @param id the ID of the order to retrieve
    * @return a String representing the line from the file with the matching ID, or null if not found
    * @throws FileNotFoundException if the dbOrders.txt file cannot be found
    */
    public String fromOrders(int id) throws FileNotFoundException {
        File file = new File(basePath + "/dbOrders.txt");
        Scanner scanner = new Scanner(file);
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
    
            String[] parts = line.split("\\|");
            int lineId = Integer.parseInt(parts[0].substring(8));
    
            if (lineId == id) {
                scanner.close();
                return line;
            }
        }
    
        scanner.close();
        return null;
    }

   /**
    * Deletes the line with the provided ID from the dbOrder.txt file
    * @param id the ID of the order to delete
    * @throws IOException if an I/O error occurs while reading or writing to the file
    */
    public void deleteOrder(int id) throws IOException {
        File file = new File(basePath + "/dbOrders.txt");
        File tempFile = new File("tempFile.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
    
        while ((currentLine = reader.readLine()) != null) {
            String[] parts = currentLine.split("\\|");
            int lineId = Integer.parseInt(parts[0].substring(8));
            
            if (lineId != id) {
                writer.write(currentLine + "\n");
            }
        }
    
        writer.close();
        reader.close();
        file.delete();
        tempFile.renameTo(file);
    }




   /**
    * Retrieves a line from the dbProducts.txt file based on the provided ID
    * @param id the ID of the product to retrieve
    * @return a String representing the line from the file with the matching ID, or null if not found
    * @throws FileNotFoundException if the dbProducts.txt file cannot be found
    */
    public ArrayList<Shoe> searchProducts(String search) throws FileNotFoundException {
        
        ArrayList<Shoe> list = new ArrayList<Shoe>();
        File file = new File(basePath + "/dbProducts.txt");
        Scanner scanner = new Scanner(file);
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
    
            String[] parts = line.split("\\|");
            //int lineId = Integer.parseInt(parts[0].substring(3));
            
            for (int i = 1; i < 7; i++) {
                int startIndex = parts[i].indexOf(":") + 1;
                String substring = parts[i].substring(startIndex);
                substring = substring.trim();

                // checks if keyword is contained within any of the fields
                if (search.toLowerCase().contains(substring.toLowerCase())){
                    Map<String, String> map = lineToMap(line);
                    Shoe shoe = mapToShoe(map);
                    list.add(shoe); 

                }
                
            }
            
        }
    
        scanner.close();
        return list;
    }

    /**
     * Converts information from the Product, User or Order file to a Map information can be called through key value pairs
     * @param line the line from the file to be converted
     * @return a Map containing the information from Product, User or Order
     */
    public Shoe mapToShoe(Map<String, String> map) {
        Shoe shoe = new Shoe(Integer.parseInt(map.get("ID")), map.get("Name"),Double.parseDouble(map.get("Price")), Integer.parseInt(map.get("Stock count")), 
        map.get("Description"), map.get("Type"), map.get("Size"), map.get("Colour"), map.get("Brand"), map.get("Added by"));

        //converting the reviews from text format to a review object
        String reviewString = map.get("Reviews");
        
        if (!reviewString.equals("[]")){
            String[] allReviews = reviewString.substring(1, reviewString.length()-1).split(","); // removes square brackets from the string

            for (String r:allReviews){
                //System.out.println(r);
                String[] review = r.substring(1, r.length()-1).split("&"); // removes the outer (), and separates the rating and comment
                Double rating = Double.parseDouble(review[0].split("=")[1]); // extracts the rating from the review string
                String com = review[1].split("=")[1]; // extracts the comment from the review string
                shoe.addReview(new Review(rating, com));
            }
        }
        
        //System.out.println(shoe.toDatabase());

        return shoe;
        
    }

    /**
     * Converts information from the Product, User or Order map into a useable instance of that object (not actual copy is returned)
     * @param line the line from the file to be converted
     * @return a Map containing the information from Product, User or Order
     */
    public Shopper mapToShopper(Map<String, String> map) {
        Shopper shopper;

        // grab all user info from map
        String id = map.get("AccID");
        String name = map.get("AccName");
        String stringCart = map.get("AccCart");
        String stringWish = map.get("AccWishlist");
        String email = map.get("AccEmail");
        String pass = map.get("AccPassword");
        String phone = map.get("AccPhone");
        String address = map.get("AccAddr");
        String stringOrders = map.get("AccOrders");

        if (address == null){
            if (phone == null){
                shopper = new Shopper(name, "", pass, email);
            }else {
                shopper = new Shopper(name, "", phone, pass, email);
            }
        }else{
            shopper = new Shopper(name, address, phone, pass, email);
        }

        // grabbing the order from the collection of orders, and coverting it into an order object that's in our shopper instance
        if (!stringOrders.equals("[]")){
            String[] allOrders = stringOrders.substring(1, stringOrders.length()-1).split(",");

            int orderIndex = 0; // to track the position of the order we're setting up

            for (String o:allOrders){
                int orderID = Integer.parseInt(o.split("=")[1].trim());

                try {
                    String orderLine = fromOrders(orderID);
                    Map<String, String> orderMap = lineToMap(orderLine);

                    System.out.println(orderMap);
                    
                    String cartItems = orderMap.get("CartItems");
                    String orderStatus = orderMap.get("Status");
                    String orderDate = orderMap.get("OrderDate");
                    String orderEst = orderMap.get("EstimatedDate");

                    String[] allProds = cartItems.substring(1, cartItems.length()-1).split(",");

                    for (String p:allProds){
                        int prodID_c = Integer.parseInt(p.split("=")[0].trim());
                        int prodQ_c = Integer.parseInt(p.split("=")[1]);
        
                        //System.out.println(p);
                        //System.out.println(prodID_c);
                        //System.out.println(prodQ_c);
        
                        try {
                            String shoeString = fromProducts(prodID_c);
                            
                            //System.out.println(shoeString);
        
                            // just in-case the product no longer exists
                            if (!shoeString.equals("")){
        
                                Map<String, String> shoeMap = lineToMap(shoeString);
                                Shoe s = mapToShoe(shoeMap);
            
                                shopper.addToCart(s, prodQ_c);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    // take the cart from the order, and place it as a 'new' order
                    shopper.placeOrder();
                    
                    // instead of everything being randomly/newly generated, we simply take the properties from the database
                    shopper.getOrder().get(orderIndex).setID(orderID);
                    shopper.getOrder().get(orderIndex).setStatus(orderStatus);
                    shopper.getOrder().get(orderIndex).setOrderDate(new Date(Long.parseLong(orderDate)));
                    shopper.getOrder().get(orderIndex).setEstimatedDate(new Date(Long.parseLong(orderEst)));

                    orderIndex++;
                    
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        //converting cart from text format to shoe objects to be added to the instance cart
        if (!stringCart.equals("{}")){
            String[] allProds = stringCart.substring(1, stringCart.length()-1).split(",");

            for (String p:allProds){
                int prodID_c = Integer.parseInt(p.split("=")[0].trim());
                int prodQ_c = Integer.parseInt(p.split("=")[1]);

                System.out.println(p);
                System.out.println(prodID_c);
                System.out.println(prodQ_c);

                try {
                    String shoeString = fromProducts(prodID_c);
                    
                    System.out.println(shoeString);

                    // just in-case the product no longer exists
                    if (!shoeString.equals("")){

                        Map<String, String> shoeMap = lineToMap(shoeString);
                        Shoe s = mapToShoe(shoeMap);
    
                        shopper.addToCart(s, prodQ_c);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        //converting wishlist from text format to shoe objects to be added to the instance wishlist
        if (!stringWish.equals("{}")){
            String[] allProds = stringWish.substring(1, stringWish.length()-1).split(",");

            for (String p:allProds){
                int prodID_w = Integer.parseInt(p.split("=")[0].trim());
                int prodQ_w = Integer.parseInt(p.split("=")[1]);

                System.out.println(prodID_w);

                try {
                    String shoeString = fromOrders(prodID_w);

                    System.out.println(shoeString); //

                    // just in-case the product no longer exists
                    if (shoeString != null){

                        Map<String, String> shoeMap = lineToMap(shoeString);
                        Shoe s = mapToShoe(shoeMap);
    
                        shopper.addToWishlist(s, prodQ_w);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        
        shopper.setID(Integer.parseInt(id)); // replaces randomly generated id
        return shopper;
    }

    // converts the string representation of a cart, to an actual cart instance
    public Cart lineToCart(String stringCart){
        Cart c = new Cart();

        if (!stringCart.equals("{}")){
            String[] allProds = stringCart.substring(1, stringCart.length()-1).split(",");

            for (String p:allProds){
                int prodID_c = Integer.parseInt(p.split("=")[0]);
                int prodQ_c = Integer.parseInt(p.split("=")[1]);

                try {
                    String shoeString = fromProducts(prodID_c);
                    
                    System.out.println(shoeString);

                    // just in-case the product no longer exists
                    if (!shoeString.equals("")){

                        Map<String, String> shoeMap = lineToMap(shoeString);
                        Shoe s = mapToShoe(shoeMap);

                        c.addItem(s, prodQ_c);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            return c;
        }
        
        return null;
    }


    /**
     * Sorts an arraylist of shoe objects based on price (lowest to highest)
     * @param ArrayList<Shoe> array list of shoe objects
     * @return ArrayList<Shoe> of sorted shoe objects
     */
    public static ArrayList<Shoe> sortShoesByPrice(ArrayList<Shoe> shoes) {
        Collections.sort(shoes, new Comparator<Shoe>() {
            public int compare(Shoe s1, Shoe s2) {
                if (s1.getPrice() < s2.getPrice()) {
                    return -1;
                } else if (s1.getPrice() > s2.getPrice()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return shoes;
    }

    /**
     * authenticates email and password of user
     * @param email email string
     * @param pass password string
     * @return the account id of the user
     */
    public String authenticateUsers(String email, String pass, String type) throws IOException {
        File file = new File(basePath + "/dbUsers.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
   
                String[] parts = line.split("\\|");
                String accID = (parts[0].substring(6));
                String accEmail = (parts[2].substring(9));
                String accPass = (parts[3].substring(12));
                String accType = (parts[4].substring(8));

                if (accEmail.equals(email) && accPass.equals(pass) && accType.equals(type)) {
                   return accID;
                }
            }

            scanner.close();
        }
    
        return null;
    }
}