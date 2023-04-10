import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;



// the page that shows up after you search for something, contains an instance of the ProductPage class
public class ShopResults extends JPanel implements ListSelectionListener, ActionListener{
    private Database db;
    private ArrayList<Shoe> results;
    private Shopper shopper;
    private Shoe[] result_list;
    private JList<Shoe> product_list;
    private DefaultListModel<Shoe> product_ListModel; // a built-in class that allows us to manipulate our Jist
    private JScrollPane product_list_scroll;

    // for the product info panel
    private JPanel product_info; // container for the product info
    private JTextArea reviews; // for containing all the reviews
    private JScrollPane review_scroller; // to make the text area scrollable
    private JLabel prod_id;
    private JLabel prod_name; 
    private JLabel prod_price;
    private JLabel prod_stock;
    private JLabel prod_desc;
    private JLabel prod_type;
    private JLabel prod_size;
    private JLabel prod_colour;
    private JLabel prod_brand;
    private JLabel prod_seller;
    private JLabel prod_reviews;
    private JLabel prod_notFound;

    // for the side menu panel
    private JPanel side_menu; // container for side menu buttons
    private JButton sort_button;
    private JButton add_to_cart_button;
    private JButton add_to_wishlist_button;
    private JButton review_button;
    private JButton back_button;

    public ShopResults(Database data, ArrayList<Shoe> shoes, Shopper sh){
        this.results = shoes;
        this.db = data;
        this.shopper = sh;

        result_list = results.toArray(new Shoe[0]); // converting the arraylist of products into a regular list for the JList

        // setting up side menu
        GridLayout side_menu_layout = new GridLayout(5, 1);
        side_menu_layout.setVgap(50);

        side_menu = new JPanel(side_menu_layout);
        side_menu.setBorder(new EmptyBorder(50, 50, 50, 50));

        sort_button = new JButton("SORT BY PRICE (asc)");
        sort_button.setBackground(Color.LIGHT_GRAY);
        sort_button.setFont(new Font("Sans-Serif", Font.BOLD, 20));

        add_to_cart_button = new JButton("ADD TO CART");
        add_to_cart_button.setBackground(Color.LIGHT_GRAY);
        add_to_cart_button.setFont(new Font("Sans-Serif", Font.BOLD, 20));

        add_to_wishlist_button = new JButton("ADD TO WISHLIST");
        add_to_wishlist_button.setBackground(Color.LIGHT_GRAY);
        add_to_wishlist_button.setFont(new Font("Sans-Serif", Font.BOLD, 20));

        review_button = new JButton("LEAVE A REVIEW");
        review_button.setBackground(Color.LIGHT_GRAY);
        review_button.setFont(new Font("Sans-Serif", Font.BOLD, 20));

        back_button = new JButton("< BACK");
        back_button.setBackground(Color.LIGHT_GRAY);
        back_button.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        
        side_menu.add(sort_button);
        side_menu.add(add_to_cart_button);
        side_menu.add(add_to_wishlist_button);
        side_menu.add(review_button);
        side_menu.add(back_button);

        // ADD BUTTONS TO ACTION LISTENER SO THEY DO SOMETHING
        sort_button.addActionListener(this);
        review_button.addActionListener(this);
        add_to_cart_button.addActionListener(this);
        add_to_wishlist_button.addActionListener(this);

        // set up list, using a default list model which handles all the operations within a JList
        product_ListModel = new DefaultListModel<Shoe>(); 

        for (Shoe s: result_list){
            product_ListModel.addElement(s);
        }

        // add the JList to the list model, and configure initial properties
        product_list = new JList<Shoe>(product_ListModel);
        product_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        product_list.setSelectedIndex(0);
        product_list.addListSelectionListener(this);
        product_list_scroll = new JScrollPane(product_list); // add the ability to scroll

        product_info = new JPanel(new GridLayout(1, 1)); // set it to grid layout if the list isn't empty

        if (product_ListModel.isEmpty()){
            product_info.setLayout(new GridBagLayout()); // we're using this layout so the text can be centered
            prod_notFound = new JLabel("Your search returned no results.");
            prod_notFound.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
            product_info.add(prod_notFound);

            sort_button.setEnabled(false);
            add_to_cart_button.setEnabled(false);
            add_to_wishlist_button.setEnabled(false);
            review_button.setEnabled(false);
        } else{
            GridLayout grid = new GridLayout(12, 1);
            product_info.setLayout(grid);
            product_info.setBorder(new EmptyBorder(25, 25, 25, 25));
            grid.setVgap(15);

            // initialize all components
            prod_id = new JLabel();
            prod_id.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            prod_name = new JLabel();
            prod_name.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            prod_price = new JLabel();
            prod_price.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            prod_stock = new JLabel();
            prod_stock.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            prod_desc = new JLabel();
            prod_desc.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            prod_type = new JLabel();
            prod_type.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            prod_size = new JLabel();
            prod_size.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            prod_colour = new JLabel();
            prod_colour.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            prod_brand = new JLabel();
            prod_brand.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            prod_seller = new JLabel();
            prod_seller.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            prod_reviews = new JLabel("REVIEWS:");
            prod_reviews.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

            reviews = new JTextArea();
            reviews.setEditable(false);
            review_scroller = new JScrollPane(reviews);

            Shoe s = product_ListModel.firstElement();
            setProductInfo(Integer.toString(s.getId()), s.getName(), Double.toString(s.getPrice()), Integer.toString(s.getStockCount()), s.getDescription(), s.getType(), s.getSize(), s.getColour(), s.getBrand(), s.getAddedBy(), s.getReviews());
            
            product_info.add(prod_id);
            product_info.add(prod_name);
            product_info.add(prod_price);
            product_info.add(prod_stock);
            product_info.add(prod_desc);
            product_info.add(prod_type);
            product_info.add(prod_size);
            product_info.add(prod_colour);
            product_info.add(prod_brand);
            product_info.add(prod_seller);
            product_info.add(prod_reviews);
            product_info.add(review_scroller);
        }
    }

    public void pageInit(){
        setLayout(new GridLayout(1, 3));
        add(product_list_scroll);
        add(product_info);
        add(side_menu);
    }

    public JButton getBackButton(){
        return this.back_button;
    }
    
    public JButton getReviewButton(){
        return this.review_button;
    }

    public void setProductInfo(String p_id, String p_name, String p_price, String p_stock, String p_desc, String p_type, String p_size, String p_col, String p_brand, String p_seller, ArrayList<Review> p_reviews){
        prod_id.setText("PRODUCT ID: " + p_id); 
        prod_name.setText("NAME: " + p_name); 
        prod_price.setText("PRICE: " + p_price);
        prod_stock.setText("STOCK: " + p_stock);
        prod_desc.setText("DESCRIPTION: " + p_desc);
        prod_type.setText("TYPE: " + p_type);
        prod_size.setText("SIZE: " + p_size);
        prod_colour.setText("COLOUR: " + p_col);
        prod_brand.setText("BRAND: " + p_brand);
        prod_seller.setText("SELLER: " + p_seller);

        String review_string = "";

        // Format each review and put it on the jtextarea
        for (Review r:p_reviews){
            review_string += "Rating: " + r.viewRating() + "\nComment: " + r.viewReview() + "\n------\n";
        }

        // If there are no reviews, then correct the output.
        if (review_string.equals("")){
            reviews.setText("This product does not currently have any reviews");
        } else{
            reviews.setText(review_string.trim());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e){
        Shoe s = product_list.getSelectedValue();
        if (s != null){
            setProductInfo(Integer.toString(s.getId()), s.getName(), Double.toString(s.getPrice()), Integer.toString(s.getStockCount()), s.getDescription(), s.getType(), s.getSize(), s.getColour(), s.getBrand(), s.getAddedBy(), s.getReviews());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sort_button){
            // sorts the results, and re-adds them onto the screen in sorted order
            result_list = Database.sortShoesByPrice(this.results).toArray(new Shoe[0]);
            product_ListModel.clear();

            for (Shoe s: result_list){
                product_ListModel.addElement(s);
            }

            product_list.setSelectedIndex(0);
        }

        if (e.getSource() == review_button){
            String reviewRating = (String)JOptionPane.showInputDialog(product_info, "Enter a rating:", "REVIEW (RATING)", JOptionPane.QUESTION_MESSAGE);
            
            try{
                Double r = Double.parseDouble(reviewRating);

                if (r > 5.0 || r < 0.0){
                    JOptionPane.showMessageDialog(product_info, "You cannot exceed a rating of 5/5, or go below 0/5!", "Oops!", JOptionPane.ERROR_MESSAGE);
                } else{
                    String reviewComment = (String)JOptionPane.showInputDialog(product_info, "Enter your thoughts on the product:", "REVIEW (COMMENT)", JOptionPane.QUESTION_MESSAGE);
                    
                    if (reviewComment.equals("")){
                        reviewComment = "N/A";
                    }

                    Shoe s = product_list.getSelectedValue();
                    s.addReview(new Review(r, reviewComment));
                    setProductInfo(Integer.toString(s.getId()), s.getName(), Double.toString(s.getPrice()), Integer.toString(s.getStockCount()), s.getDescription(), s.getType(), s.getSize(), s.getColour(), s.getBrand(), s.getAddedBy(), s.getReviews());

                    try {
                        // overwrites, the current save of the product within the database
                        this.db.deleteProduct(s.getId());
                        this.db.writeProducts(s);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }catch (NullPointerException e1){
                JOptionPane.showMessageDialog(product_info, "You cannot leave the rating empty...", "Oops!", JOptionPane.ERROR_MESSAGE);
            }catch (NumberFormatException e2){
                JOptionPane.showMessageDialog(product_info, "Your input was not a valid number.", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == add_to_cart_button){
            Shoe s = product_list.getSelectedValue();

            if (this.shopper.getCart().getItems().get(s.getId()) != null){
                JOptionPane.showMessageDialog(product_info, "This item is already in your cart!", "Oops!", JOptionPane.ERROR_MESSAGE);
            } else {
                String itemQuantity = (String)JOptionPane.showInputDialog(product_info, "How much of this item would you like to add?", "ADD TO CART", JOptionPane.QUESTION_MESSAGE);

                try {
                    int q = Integer.parseInt(itemQuantity);

                    if (q > s.getStockCount()){
                        JOptionPane.showMessageDialog(product_info, "The quantity specified exceeds the amount in stock.", "Oops!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        this.shopper.addToCart(s, q);

                        db.deleteUser(this.shopper.getId());
                        db.writeUsers(shopper);
                    }

                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(product_info, "You cannot leave the rating empty...", "Oops!", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e2){
                    JOptionPane.showMessageDialog(product_info, "Your input was not a valid number.", "Oops!", JOptionPane.ERROR_MESSAGE);
                } catch (IOException i1) {
                    i1.printStackTrace();
                }
            }
        }

        if (e.getSource() == add_to_wishlist_button){
        }

    }
}
