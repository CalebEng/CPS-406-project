import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartPage extends JPanel implements ActionListener{
    private JScrollPane cart_panel;
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
        cart_display.setBorder(new EmptyBorder(25, 25, 25, 25));
        this.cart_display.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        
        this.cart_panel = new JScrollPane(cart_display);

        GridLayout button_grid = new GridLayout(1, 5);
        this.bottom_menu = new JPanel(button_grid);
        this.bottom_menu.setBorder(new EmptyBorder(25, 25, 25, 25));
        button_grid.setHgap(25);

        this.checkout_button = new JButton("PROCEED TO CHECKOUT");
        this.checkout_button.setBackground(Color.LIGHT_GRAY);

        if (shopper.getCart().getTotalItems() == 0){
            this.checkout_button.setEnabled(false);
        } else{
            this.checkout_button.setEnabled(true);
            this.checkout_button.addActionListener(this);
        }

        this.remove_button = new JButton("REMOVE ITEM FROM CART");
        this.remove_button.setBackground(Color.LIGHT_GRAY);

        if (shopper.getCart().getTotalItems() == 0){
            this.remove_button.setEnabled(false);
        } else{
            this.remove_button.setEnabled(true);
            this.remove_button.addActionListener(this);
        }

        this.change_quantity = new JButton("CHANGE ITEM QUANTITY");
        this.change_quantity.setBackground(Color.LIGHT_GRAY);

        if (shopper.getCart().getTotalItems() == 0){
            this.change_quantity.setEnabled(false);
        } else{
            this.change_quantity.setEnabled(true);
            this.change_quantity.addActionListener(this);
        }

        this.back_button = new JButton("< BACK");
        this.back_button.setBackground(Color.LIGHT_GRAY);

        this.bottom_menu.add(this.checkout_button);
        this.bottom_menu.add(this.remove_button);
        this.bottom_menu.add(this.change_quantity);
        this.bottom_menu.add(this.back_button);
    }

    public void pageInit(){
        setLayout(new GridLayout(2, 1));

        add(cart_panel);
        add(bottom_menu);
    }

    public void updateCartDisplay(){
        this.cart_display.setText(this.shopper.getCart().display());
    }

    public JButton getBackButton(){
        return this.back_button;
    }

    public JButton getCheckoutButton(){
        return this.checkout_button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.remove_button){
            String shoeID = JOptionPane.showInputDialog(this, "ENTIRE THE ID OF THE SHOE YOU WOULD LIKE TO REMOVE:", "REMOVE ITEM", JOptionPane.PLAIN_MESSAGE);

            try {
                if (this.shopper.getCart().getItems().get(Integer.parseInt(shoeID)) != null){
                    // re-add the stock to the database
                    Shoe s = this.db.mapToShoe(this.db.lineToMap(this.db.fromProducts(Integer.parseInt(shoeID))));
                    s.addStock(this.shopper.getCart().getItems().get(Integer.parseInt(shoeID)));
                    this.db.deleteProduct(Integer.parseInt(shoeID));
                    this.db.writeProducts(s);

                    // remove the item from the cart
                    this.shopper.getCart().removeItem(Integer.parseInt(shoeID));
                    updateCartDisplay();

                    // overwrite shopper info with updated cart
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
                if (this.shopper.getCart().getItems().get(Integer.parseInt(shoeID)) != null){
                    String new_quant = JOptionPane.showInputDialog(this, "ENTER THE NEW QUANTITY:", "EDIT ITEM", JOptionPane.PLAIN_MESSAGE);

                    // re-add the stock to the database
                    Shoe s = this.db.mapToShoe(this.db.lineToMap(this.db.fromProducts(Integer.parseInt(shoeID))));

                    // remove the item from the cart
                    this.shopper.getCart().removeItem(Integer.parseInt(shoeID));
                    this.shopper.getCart().addItem(s, Integer.parseInt(new_quant));
                    updateCartDisplay();

                    // overwrite shopper info with updated cart
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
