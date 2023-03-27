package gui;

import javax.swing.*;

public class LoginPage extends JPanel{
    private JLabel email_label;
    private JLabel pass_label;
    private JTextField email_txt;
    private JTextField pass_txt;
    private JButton login_button;

    public LoginPage(){
        // set/configures properties of all components on the page
        // setting up/creating new labels and buttons for email + password inputs, also sets the position and size of each component

        // email input
        email_label = new JLabel("Email:");
        email_label.setBounds(10, 20, 80, 25);

        email_txt = new JTextField();
        email_txt.setBounds(100, 20, 80, 25);

        // password input
        pass_label = new JLabel("Password:");
        pass_label.setBounds(10, 50, 80, 25);

        pass_txt = new JTextField();
        pass_txt.setBounds(100, 50, 80, 25);

        //login button
        login_button = new JButton("login");
        login_button.setBounds(10, 200, 80, 25);
    }

    // intializes the components onto the panel
    public void pageInit(){
        setLayout(null);

        add(email_label);
        add(email_txt);

        add(pass_label);
        add(pass_txt);

        add(login_button);
    }

    // returns the actual button object for the login button
    public JButton geButton(){
        return this.login_button;
    }

    // returns the email entered by the user
    public String getEmail(){
        return this.email_txt.getText();
    }

    // returns the password entered by the user
    public String getPass(){
        return this.pass_txt.getText();
    }
}
