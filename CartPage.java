import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CartPage extends JPanel implements ActionListener{
    private JPanel cart_panel;
    private JTextArea cart_display;
    private JPanel bottom_menu;
    private JButton checkout_button;
    private JButton remove_button;
    private JButton change_quantity;
    private JButton back_button;
    private Database db;
    private Shopper shopper;

    CartPage(Database data, Shopper shop){
        this.db = data;
        this.shopper = shop;

        this.cart_display = new JTextArea(this.shopper.getCart().display());
        this.cart_display.setEditable(false);

        this.bottom_menu = new JPanel(new GridLayout(1, 5));

        this.checkout_button = new JButton("PROCEED TO CHECKOUT");
        this.remove_button = new JButton("REMOVE ITEM FROM CART");
        this.change_quantity = new JButton("CHANGE ITEM QUANTITY");
        this.back_button = new JButton("< BACK");

        this.bottom_menu.add(this.checkout_button);
        this.bottom_menu.add(this.remove_button);
        this.bottom_menu.add(this.change_quantity);
        this.bottom_menu.add(this.back_button);
    }

    public void pageInit(){
        setLayout(new GridLayout(2, 1));

        add(cart_display);
        add(bottom_menu);
    }

    public void updateCart(){
        this.cart_display.setText(this.shopper.getCart().display());
    }

    public JButton getBackButton(){
        return this.back_button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
