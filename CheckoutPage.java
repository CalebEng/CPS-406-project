import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CheckoutPage extends JPanel{
    private JLabel name_field;
    private JTextField user_name;
    private JLabel address_field;
    private JTextField user_address;
    private JLabel email_field;
    private JTextField user_email;
    private JLabel phone_field;
    private JTextField user_phone;
    private JLabel payment_field;
    private JTextField user_payment;
    private JButton place_order;
    private JButton back_button;
    private Database db;
    private Shopper shopper;

    public CheckoutPage(Database data, Shopper shop){
        this.db = data;
        this.shopper = shop;

        this.name_field = new JLabel("name:");
        this.name_field.setBounds(413, 64, 56, 23);

        this.user_name = new JTextField();
        this.user_name.setBounds(413, 105, 454, 41);

        this.address_field = new JLabel("address:");
        this.address_field.setBounds(413, 164, 77, 23);

        this.user_address = new JTextField();
        this.user_address.setBounds(413, 205, 454, 41);

        this.email_field = new JLabel("email:");
        this.email_field.setBounds(413, 264, 54, 23);

        this.user_email = new JTextField();
        this.user_email.setBounds(413, 305, 454, 41);

        this.phone_field = new JLabel("phone number:");
        this.phone_field.setBounds(413, 364, 135, 23);

        this.user_phone = new JTextField();
        this.user_phone.setBounds(413, 405, 454, 41);
        
        this.payment_field = new JLabel("card info:");
        this.payment_field.setBounds(413, 464, 83, 23);

        this.user_payment = new JTextField();
        this.user_payment.setBounds(413, 505, 454, 41);

        this.place_order = new JButton("PLACE ORDER");
        this.place_order.setBounds(413, 606, 213, 50);
        this.place_order.setFont(new Font("Sans-Serif", Font.PLAIN, 22));
        this.place_order.setBackground(Color.LIGHT_GRAY);

        this.back_button = new JButton("< BACK");
        this.back_button.setBounds(692, 606, 175, 50);
        this.back_button.setFont(new Font("Sans-Serif", Font.PLAIN, 22));
        this.back_button.setBackground(Color.LIGHT_GRAY);

        autoFill();
    }

    public void pageInit(){
        setLayout(null);

        add(this.name_field);
        add(this.user_name);

        add(this.address_field);
        add(this.user_address);

        add(this.email_field);
        add(this.user_email);

        add(this.phone_field);
        add(this.user_phone);

        add(this.payment_field);
        add(this.user_payment);

        add(this.place_order);
        add(this.back_button);
    }

    // fills in any info stored by the database
    public void autoFill(){
        this.user_name.setText(this.shopper.getName());
        this.user_address.setText(this.shopper.getaddress());
        this.user_email.setText(this.shopper.getEmail());
        this.user_phone.setText(this.shopper.getphoneNumber());
    }

    // checks for any empty field, true if everything entered, false if a field is empty.
    public boolean allFieldsEntered(){
        return !this.user_name.getText().equals("") && !this.user_address.getText().equals("") && !this.user_email.getText().equals("") && !this.user_phone.getText().equals("") && !this.user_payment.getText().equals("");
    }

    public JButton getBackButton(){
        return this.back_button;
    }

    public JButton getOrderButton(){
        return this.place_order;
    }
    
}
