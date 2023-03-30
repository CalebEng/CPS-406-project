/*
 * Yong Tan
 * 3/23/2023
 * CPS-406
 * Shoe class:
 */

import java.util.List;
import java.util.ArrayList;


// This class represents a shoe object with various attributes such as id, name, price, stock count, description,
// type, size, colour, brand and who it is added by. It also contains a list of reviews for the shoe.
public class Shoe {
    private int id;
    private String name;
    private double price;
    private int stockCount;
    private String description;
    private String type;
    private String size;
    private String colour;
    private String brand;
    private String addedBy;
    private List<String> reviews;

 // Constructor to initialize the shoe object with the given attributes.
    public Shoe(int id, String name, double price, int stockCount, String description, String type, String size,
            String colour, String brand, String addedBy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockCount = stockCount;
        this.description = description;
        this.type = type;
        this.size = size;
        this.colour = colour;
        this.brand = brand;
        this.addedBy = addedBy;
        this.reviews = new ArrayList<>(); // Initialize the reviews list as an empty ArrayList.
    }

// Getters to access the various attributes of the shoe.   
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockCount() {
        return stockCount;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }

    public String getBrand() {
        return brand;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public List<String> getReviews() {
        return reviews;
    }

    // Method to add a review to the list of reviews for the shoe.    
    public void addReview(String review) {
        reviews.add(review);
    }
    
    // method to add stock
    public void addStock(int amount) {
        stockCount += amount;
    }

    // method to remove stock
    //If there is no stock available to remove(stock of 0), shows first message.
    //If there is some stock available, but the removing amount exceeds the stock amount, shows second message.
    public void removeStock(int amount) {
        if (stockCount >= amount) {
            stockCount -= amount;
        } else if (stockCount == 0) {
            throw new IllegalArgumentException("No stock available to remove");
        } else {
            throw new IllegalArgumentException("Not enough stock available");
        }
    }
    
    public String toDatabase() {
        return "ID:" + id + 
            "|Name:" + name +
            "|Brand:" + brand +
            "|Type:" + type +
            "|Size:" + size +
            "|Colour:" + colour + 
            "|Price:" + price +
            "|Stock count:" + stockCount +
            "|Added by:" + addedBy + 
            "|Description:" + description + 
            "|Reviews:" + reviews + "\n";
    }
    
//@returns info for shoe class    
    public String toString() {
        return "Shoe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockCount=" + stockCount +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", colour='" + colour + '\'' +
                ", brand='" + brand + '\'' +
                ", addedBy='" + addedBy + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
