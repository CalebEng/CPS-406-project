import javax.swing.*;
import java.awt.*;

// If you break it down, the login page is basically a container (is the main panel), that contains a collection of components/sub-panels
public class LoginPage extends JPanel {
    private JLabel login_title;
    private JLabel email_label;
    private JLabel pass_label;
    private JTextField email_txt;
    private JTextField pass_txt; //TODO: turn this into a password field later
    private JButton login_button;
    private JLabel type_label;
    private JComboBox type_indicator;
    private String[] user_types = {"Shopper", "Seller", "Admin"};
    private JLabel not_reg;
    private JButton register_button;

    // set/configures properties of all components on the page
    public LoginPage(){
        // login page title properties
        login_title = new JLabel("[GNRC SHOE STORE]");
        login_title.setBounds(306, 68, 700, 100);
        login_title.setFont(new Font("Sans-Serif", Font.BOLD, 64));

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

        //login button properties
        login_button = new JButton("LOGIN");
        login_button.setBounds(413, 446, 176, 50);
        login_button.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        login_button.setBackground(Color.LIGHT_GRAY);

        //type indicator properties, the components that help indicate the user's type (i.e. are they and admin, shopper, or seller)
        type_label = new JLabel("AS...");
        type_label.setBounds(612, 457, 60, 35);
        type_label.setFont(new Font("Sans-Serif", Font.PLAIN, 24));

        type_indicator = new JComboBox<String>(user_types);
        type_indicator.setBackground(Color.LIGHT_GRAY);
        type_indicator.setBounds(687, 446, 180, 50);

        //regisration label + button properties
        not_reg = new JLabel("not a user yet?");
        not_reg.setBounds(561, 560, 165, 35);
        not_reg.setFont(new Font("Sans-Serif", Font.PLAIN, 24));

        register_button = new JButton("REGISTER");
        register_button.setBounds(553, 603, 175, 50);
        register_button.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        register_button.setBackground(Color.LIGHT_GRAY);
    }

    // intializes the components onto the panel
    public void pageInit(){
        setLayout(null);

        add(login_title);

        add(email_label);
        add(email_txt);

        add(pass_label);
        add(pass_txt);

        add(login_button);

        add(type_label);
        add(type_indicator);

        add(not_reg);
        add(register_button);
    }

    // returns the actual button object for the login button
    public JButton getLoginButton(){
        return this.login_button;
    }

    // returns the button object for the registration button for new users
    public JButton getRegistrationButton(){
        return this.register_button;
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
