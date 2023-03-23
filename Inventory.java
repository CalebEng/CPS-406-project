import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Shoe> shoes;

    public Inventory() {
        shoes = new ArrayList<>();
    }

    public void addShoe(Shoe shoe) {
        shoes.add(shoe);
    }

    public List<Shoe> getShoes() {
        return shoes;
    }
}
