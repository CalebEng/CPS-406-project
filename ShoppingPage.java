import javax.swing.*;
import java.awt.*;

public class ShoppingPage extends JPanel {
    private JTextField search_bar;
    private JLabel welcome_msg;
    private JButton search_button;
    private JButton account_button;
    private JButton cart_button;
    private JButton wishlist_button;
    private JButton orders_button;

    public ShoppingPage(){
        // set/configures properties of all components on the page
        // setting up/creating new labels and buttons for search bar and buttons, also sets the position and size of each component
        
        // search bar
        search_bar = new JTextField();
        search_bar.setBounds(255, 119, 673, 53);

        // search button
        search_button = new JButton("search");
        search_button.setBounds(928, 119, 97, 53);
        search_button.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        search_button.setBackground(Color.LIGHT_GRAY);

        // welcome message
        welcome_msg = new JLabel("WELCOME TO THE SHOPPING DASHBOARD...");
        welcome_msg.setBounds(201, 257, 900, 46);
        welcome_msg.setFont(new Font("Sans-Serif", Font.BOLD, 40));
        
        // ALL BUTTONS

        // view account button properties
        account_button = new JButton("VIEW YOUR ACCOUNT");
        account_button.setBounds(76, 389, 264, 213);
        account_button.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        account_button.setBackground(Color.LIGHT_GRAY);

        // view cart button properties
        cart_button = new JButton("VIEW YOUR CART");
        cart_button.setBounds(367, 389, 264, 213);
        cart_button.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        cart_button.setBackground(Color.LIGHT_GRAY);

        // view wishlist button properties
        wishlist_button = new JButton("VIEW YOUR WISHLIST");
        wishlist_button.setBounds(658, 389, 264, 213);
        wishlist_button.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        wishlist_button.setBackground(Color.LIGHT_GRAY);

        // view wishlist button properties
        orders_button = new JButton("VIEW YOUR ORDERS");
        orders_button.setBounds(939, 389, 264, 213);
        orders_button.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        orders_button.setBackground(Color.LIGHT_GRAY);
    }

    // intializes the components onto the panel
    public void pageInit(){
        setLayout(null);
        
        add(search_bar);
        add(search_button);

        add(welcome_msg);

        add(account_button);
        add(cart_button);
        add(wishlist_button);
        add(orders_button);
    }

    public JButton getSearchButton(){
        return this.search_button;
    }

    public JButton getAccButton(){
        return this.account_button;
    }

    // gets the search query entered by the user
    public String getQueryString(){
        return this.search_bar.getText();
    }
}
