import javax.swing.*;
import java.awt.*;

public class RegisterPage extends JPanel{
    private JLabel register_title;
    private JLabel email_label;
    private JLabel pass_label;
    private JTextField email_txt;
    private JTextField pass_txt; //TODO: turn this into a password field later
    private JButton register_button;
    private JLabel type_label;
    private JComboBox type_indicator;
    private String[] user_types = {"Shopper", "Seller"};
    private JButton back_button;

    RegisterPage(){
        // login page title properties
        register_title = new JLabel("[GNRC SHOE STORE]");
        register_title.setBounds(306, 68, 700, 100);
        register_title.setFont(new Font("Sans-Serif", Font.BOLD, 64));

        // email input properties
        email_label = new JLabel("Email:");
        email_label.setBounds(413, 194, 80, 35);
        email_label.setFont(new Font("Sans-Serif", Font.PLAIN, 24));

        email_txt = new JTextField();
        email_txt.setBounds(413, 237, 454, 41);

        // password input properties
        pass_label = new JLabel("Password:");
        pass_label.setBounds(413, 310, 150, 35);
        pass_label.setFont(new Font("Sans-Serif", Font.PLAIN, 24));

        pass_txt = new JTextField();
        pass_txt.setBounds(413, 353, 454, 41);

        //register button properties
        register_button = new JButton("REGISTER");
        register_button.setBounds(413, 446, 176, 50);
        register_button.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        register_button.setBackground(Color.LIGHT_GRAY);

        //type indicator properties, the components that help indicate the user's type (i.e. are they and admin, shopper, or seller)
        type_label = new JLabel("AS...");
        type_label.setBounds(612, 457, 60, 35);
        type_label.setFont(new Font("Sans-Serif", Font.PLAIN, 24));

        type_indicator = new JComboBox<String>(user_types);
        type_indicator.setBackground(Color.LIGHT_GRAY);
        type_indicator.setBounds(687, 446, 180, 50);

        //back button properties
        back_button = new JButton("< BACK");
        back_button.setBounds(1005, 570, 175, 50);
        back_button.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        back_button.setBackground(Color.LIGHT_GRAY);

    }

    public void pageInit(){
        setLayout(null);

        add(register_title);

        add(email_label);
        add(email_txt);

        add(pass_label);
        add(pass_txt);

        add(register_button);

        add(type_label);
        add(type_indicator);

        add(back_button);
    }

    // returns the register button object
    public JButton getRegisterButton(){
        return this.register_button;
    }

    public JButton getBackButton(){
        return this.back_button;
    }

    // returns the email entered by the user
    public String getEmail(){
        return this.email_txt.getText();
    }

    // returns the password entered by the user
    public String getPass(){
        return this.pass_txt.getText();
    }

    // gets the user's type
    public String getSelection(){
        return (String)this.type_indicator.getSelectedItem();
    }
}
