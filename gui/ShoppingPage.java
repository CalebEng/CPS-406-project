package gui;

import javax.swing.*;
import java.awt.*;

public class ShoppingPage extends JPanel {
    private JTextField search_bar;
    private JButton search_button;
    private JButton shoes_category;
    private JButton boots_category;

    public ShoppingPage(){
        // set/configures properties of all components on the page
        // setting up/creating new labels and buttons for search bar and buttons, also sets the position and size of each component
        
        // search bar
        search_bar = new JTextField();
        search_bar.setBounds(300, 10, 700, 50);

        // search button
        search_button = new JButton("search");
        search_button.setBounds(1060, 10, 80, 50);

        // shoes category button
        shoes_category = new JButton("SHOP SHOES");
        shoes_category.setBounds(300, 250, 200, 200);

        // boots category button
        boots_category = new JButton("SHOP BOOTS");
        boots_category.setBounds(700, 250, 200, 200);
    }

    // intializes the components onto the panel
    public void pageInit(){
        setLayout(null);
        
        add(search_bar);
        add(search_button);
        add(shoes_category);
        add(boots_category);
    }
}
