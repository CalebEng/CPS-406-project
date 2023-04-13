import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class OrderPage extends JPanel {
    private String order_string;
    private JScrollPane order_panel;
    private JTextArea order_display;
    private JPanel bottom_menu;
    private JButton back_button;
    private Database db;
    private Shopper shopper;

    public OrderPage(Database data, Shopper shop){
        this.db = data;
        this.shopper = shop;
        this.order_string = "";

        if (this.shopper.getOrder().size() == 0){
            this.order_string = "You have no orders";
        } else {
            for (Order ord:this.shopper.getOrder()){
                this.order_string += ord.viewOrder() + "\n";
            }
        }

        this.order_display = new JTextArea(this.order_string);
        this.order_display.setEditable(false);
        order_display.setBorder(new EmptyBorder(25, 25, 25, 25));
        this.order_display.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        
        this.order_panel = new JScrollPane(order_display);

        GridLayout button_grid = new GridLayout(1, 1);
        this.bottom_menu = new JPanel(button_grid);
        this.bottom_menu.setBorder(new EmptyBorder(25, 25, 25, 25));
        //button_grid.setHgap(25);

        this.back_button = new JButton("< BACK");
        this.back_button.setBackground(Color.LIGHT_GRAY);

        this.bottom_menu.add(back_button);
    }

    public void pageInit(){
        setLayout(new GridLayout(2, 1));

        add(order_panel);
        add(bottom_menu);
    }

    public void updateCartDisplay(){
        this.order_display.setText(this.shopper.getCart().display());
    }

    public JButton getBackButton(){
        return this.back_button;
    }
}
