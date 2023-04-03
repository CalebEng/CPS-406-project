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
            map.put(parts[0], parts[1]);
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

                if (search.equals(substring)){
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
        //System.out.println(shoe.toDatabase());

        return shoe;
        
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
     * @return true when email and password match, false when they don't
     */
    public boolean authenticateUsers(String email, String pass) throws IOException {
        File file = new File(basePath + "/dbUsers.txt");
        int inFile = 0;
        Scanner scanner = new Scanner(file);
    
        while (scanner.hasNextLine()) {
            
            String line = scanner.nextLine();
    
            String[] parts = line.split("\\|");
            String accEmail = (parts[2].substring(9));
            String accPass = (parts[3].substring(12));

            if (accEmail.equals(email) && accPass.equals(pass) ) {
               return true;
            }

            
   
        }
        scanner.close();

        return false;
    
        
    }
}