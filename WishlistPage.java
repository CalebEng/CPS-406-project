import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishlistPage extends JPanel implements ActionListener{
    private JScrollPane wishlist_panel;
    private JTextArea wishlist_display;
    private JPanel bottom_menu;
    private JButton add_to_cart_button;
    private JButton remove_button;
    private JButton change_quantity;
    private JButton back_button;
    private Database db;
    private Shopper shopper;

    WishlistPage(Database data, Shopper shop){
        this.db = data;
        this.shopper = shop;

        this.wishlist_display = new JTextArea(this.shopper.getWishlist().display());
        this.wishlist_display.setEditable(false);
        wishlist_display.setBorder(new EmptyBorder(25, 25, 25, 25));
        this.wishlist_display.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        
        this.wishlist_panel = new JScrollPane(wishlist_display);

        GridLayout button_grid = new GridLayout(1, 5);
        this.bottom_menu = new JPanel(button_grid);
        this.bottom_menu.setBorder(new EmptyBorder(25, 25, 25, 25));
        button_grid.setHgap(25);

        this.add_to_cart_button = new JButton("ADD TO CART");
        this.add_to_cart_button.setBackground(Color.LIGHT_GRAY);

        if (shopper.getWishlist().getTotalItems() == 0){
            this.add_to_cart_button.setEnabled(false);
        } else{
            this.add_to_cart_button.setEnabled(true);
            this.add_to_cart_button.addActionListener(this);
        }

        this.remove_button = new JButton("REMOVE ITEM FROM WISHLIST");
        this.remove_button.setBackground(Color.LIGHT_GRAY);

        if (shopper.getWishlist().getTotalItems() == 0){
            this.remove_button.setEnabled(false);
        } else{
            this.remove_button.setEnabled(true);
            this.remove_button.addActionListener(this);
        }

        this.change_quantity = new JButton("CHANGE ITEM QUANTITY");
        this.change_quantity.setBackground(Color.LIGHT_GRAY);

        if (shopper.getWishlist().getTotalItems() == 0){
            this.change_quantity.setEnabled(false);
        } else{
            this.change_quantity.setEnabled(true);
            this.change_quantity.addActionListener(this);
        }

        this.back_button = new JButton("< BACK");
        this.back_button.setBackground(Color.LIGHT_GRAY);

        this.bottom_menu.add(this.add_to_cart_button);
        this.bottom_menu.add(this.remove_button);
        this.bottom_menu.add(this.change_quantity);
        this.bottom_menu.add(this.back_button);
    }

    public void pageInit(){
        setLayout(new GridLayout(2, 1));

        add(wishlist_panel);
        add(bottom_menu);
    }

    public void updateWishDisplay(){
        this.wishlist_display.setText(this.shopper.getWishlist().display());
    }

    public JButton getBackButton(){
        return this.back_button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.add_to_cart_button){
            String shoeID = JOptionPane.showInputDialog(this, "ENTIRE THE ID OF THE SHOE YOU WOULD LIKE TO ADD:", "ADD TO CART", JOptionPane.PLAIN_MESSAGE);

            try {
                if (this.shopper.getWishlist().getItems().get(Integer.parseInt(shoeID)) != null){
                    // remove the item from the wishlist
                    this.shopper.getWishlist().moveToCart(this.shopper.getCart(), Integer.parseInt(shoeID));
                    updateWishDisplay();

                    // overwrite shopper info with updated wishlist
                    this.db.deleteUser(this.shopper.getId());
                    this.db.writeUsers(shopper);
                }
            } catch (Exception err) {
                //TODO: proper error catching
                err.printStackTrace();
            }
        }


        if (e.getSource() == this.remove_button){
            String shoeID = JOptionPane.showInputDialog(this, "ENTIRE THE ID OF THE SHOE YOU WOULD LIKE TO REMOVE:", "REMOVE ITEM", JOptionPane.PLAIN_MESSAGE);

            try {
                if (this.shopper.getWishlist().getItems().get(Integer.parseInt(shoeID)) != null){
                    // remove the item from the wishlist
                    this.shopper.getWishlist().removeItem(Integer.parseInt(shoeID));
                    updateWishDisplay();

                    // overwrite shopper info with updated wishlist
                    this.db.deleteUser(this.shopper.getId());
                    this.db.writeUsers(shopper);
                }
            } catch (Exception err) {
                //TODO: proper error catching
                err.printStackTrace();
            }
        }

        if (e.getSource() == change_quantity){
            String shoeID = JOptionPane.showInputDialog(this, "ENTER THE ID OF THE SHOE YOU WOULD LIKE TO EDIT:", "EDIT ITEM", JOptionPane.PLAIN_MESSAGE);
            
            try {
                if (this.shopper.getWishlist().getItems().get(Integer.parseInt(shoeID)) != null){
                    String new_quant = JOptionPane.showInputDialog(this, "ENTER THE NEW QUANTITY:", "EDIT ITEM", JOptionPane.PLAIN_MESSAGE);

                    // re-add the stock to the database
                    Shoe s = this.db.mapToShoe(this.db.lineToMap(this.db.fromProducts(Integer.parseInt(shoeID))));

                    // remove the item from the wishlist
                    this.shopper.getWishlist().removeItem(Integer.parseInt(shoeID));
                    this.shopper.getWishlist().addItem(s, Integer.parseInt(new_quant));
                    updateWishDisplay();

                    // overwrite shopper info with updated wishlist
                    this.db.deleteUser(this.shopper.getId());
                    this.db.writeUsers(shopper);
                }
            } catch (Exception err) {
                //TODO: proper error catching
                err.printStackTrace();
            }
        }
    }
}
