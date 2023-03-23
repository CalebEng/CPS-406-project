import java.util.List;
import java.util.ArrayList;


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
        this.reviews = new ArrayList<>();
    }

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

    public void addReview(String review) {
        reviews.add(review);
    }
}
