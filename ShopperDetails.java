import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Account page for the shopper
public class ShopperDetails extends JPanel implements ActionListener{
    // user object
    private Shopper acc_user;
    private Database db;

    // all labels components on page
    private JLabel page_title;
    private JLabel name_label;
    private JLabel user_name;
    private JLabel email_label;
    private JLabel user_email;
    private JLabel phone_label;
    private JLabel user_phone;
    private JLabel address_label;
    private JLabel user_address;
    private JLabel pass_label;
    private JLabel user_pass;

    // all button components on page
    private JButton edit_name;
    private JButton edit_phone;
    private JButton edit_address;
    private JButton edit_pass;
    private JButton back_button;

    ShopperDetails(Database data, Shopper user){
        this.acc_user = user;
        this.db = data;

        // configuring all labels
        // page title
        this.page_title = new JLabel("USER ACCOUNT INFO");
        this.page_title.setBounds(426, 39, 428, 46);
        this.page_title.setFont(new Font("Sans-Serif", Font.BOLD, 40));

        // shopper's name
        this.name_label = new JLabel("name:");
        this.name_label.setBounds(100, 132, 71, 28);
        this.name_label.setFont(new Font("Sans-Serif", Font.BOLD, 24));

        this.user_name = new JLabel();
        this.user_name.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        
        if (!this.acc_user.getName().equals("")){
            this.user_name.setText(this.acc_user.getName());
        }else{
            this.user_name.setText("N/A");;
        }

        this.user_name.setBounds(100, 170, 500, 23);

        // shopper's email
        this.email_label = new JLabel("email:");
        this.email_label.setBounds(100, 228, 70, 28);
        this.email_label.setFont(new Font("Sans-Serif", Font.BOLD, 24));

        this.user_email = new JLabel(this.acc_user.getEmail());
        this.user_email.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        this.user_email.setBounds(100, 266, 500, 23);
        
        // shopper's phone
        this.phone_label = new JLabel("phone:");
        this.phone_label.setBounds(100, 324, 81, 28);
        this.phone_label.setFont(new Font("Sans-Serif", Font.BOLD, 24));

        this.user_phone = new JLabel();
        this.user_phone.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

        if (!this.acc_user.getphoneNumber().equals("")){
            this.user_phone.setText(this.acc_user.getphoneNumber());;
        }else{
            this.user_phone.setText("N/A");;
        }

        this.user_phone.setBounds(100, 362, 500, 23);

        // shopper's address
        this.address_label = new JLabel("address:");
        this.address_label.setBounds(100, 420, 101, 28);
        this.address_label.setFont(new Font("Sans-Serif", Font.BOLD, 24));

        this.user_address = new JLabel();
        this.user_address.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

        if (!this.acc_user.getaddress().equals("")){
            this.user_address.setText(this.acc_user.getaddress());;
        }else{
            this.user_address.setText("N/A");
        }

        this.user_address.setBounds(100, 458, 500, 23);

        // shopper's password
        this.pass_label = new JLabel("password:");
        this.pass_label.setBounds(100, 516, 121, 28);
        this.pass_label.setFont(new Font("Sans-Serif", Font.BOLD, 24));

        // for security reasons we don't want to outright display the actual password, so we just replace it with a string of *'s
        this.user_pass = new JLabel("*".repeat(this.acc_user.getPassword().length()));
        this.user_pass.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        this.user_pass.setBounds(100, 554, 500, 23);

        // configuring all buttons
        this.edit_name = new JButton("EDIT");
        this.edit_name.setBounds(1080, 139, 100, 49);
        this.edit_name.setBackground(Color.LIGHT_GRAY);
        this.edit_name.setFont(new Font("Sans-Serif", Font.BOLD, 24));

        this.edit_phone = new JButton("EDIT");
        this.edit_phone.setBounds(1080, 331, 100, 49);
        this.edit_phone.setBackground(Color.LIGHT_GRAY);
        this.edit_phone.setFont(new Font("Sans-Serif", Font.BOLD, 24));

        this.edit_address = new JButton("EDIT");
        this.edit_address.setBounds(1080, 427, 100, 49);
        this.edit_address.setBackground(Color.LIGHT_GRAY);
        this.edit_address.setFont(new Font("Sans-Serif", Font.BOLD, 24));

        this.edit_pass = new JButton("EDIT");
        this.edit_pass.setBounds(1080, 523, 100, 49);
        this.edit_pass.setBackground(Color.LIGHT_GRAY);
        this.edit_pass.setFont(new Font("Sans-Serif", Font.BOLD, 24));

        this.back_button = new JButton("< BACK");
        this.back_button.setBounds(552, 601, 175, 50);
        this.back_button.setBackground(Color.LIGHT_GRAY);
        this.back_button.setFont(new Font("Sans-Serif", Font.BOLD, 24));

        // add all buttons to action listener
        this.edit_name.addActionListener(this);
        this.edit_phone.addActionListener(this);
        this.edit_address.addActionListener(this);
        this.edit_pass.addActionListener(this);
    }

    public void pageInit(){
        setLayout(null);
        add(this.page_title);

        add(this.name_label);
        add(this.user_name);

        add(this.email_label);
        add(this.user_email);

        add(this.phone_label);
        add(this.user_phone);

        add(this.address_label);
        add(this.user_address);

        add(this.pass_label);
        add(this.user_pass);

        add(this.edit_name);
        add(this.edit_phone);
        add(this.edit_address);
        add(this.edit_pass);
        add(back_button);
    }

    public JButton getBackButton(){
        return this.back_button;
    }

    public void changeField(String new_val, JLabel to_change){
        if (new_val.equals("")){
            JOptionPane.showMessageDialog(this, "YOU CAN'T EMPTY THE FIELD", "Oops!", JOptionPane.ERROR_MESSAGE);
        } else {
            if (to_change == user_pass){
                to_change.setText("*".repeat(new_val.length()));
                
                this.acc_user.setPassword(new_val);
            } else {
                to_change.setText(new_val);
                
                if (to_change == user_name){
                    this.acc_user.setName(new_val);
                }

                if (to_change == user_phone){
                    this.acc_user.addNumber(new_val);
                }

                if (to_change == user_address){
                    this.acc_user.setAddress(new_val);
                }
            }

            try {
                // deletes, and re-adds the new info into the database
                this.db.deleteUser(this.acc_user.getId());
                this.db.writeUsers(this.acc_user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.edit_name){
            String new_name = (String)JOptionPane.showInputDialog(this, "Enter a new name:", "NAME CHANGE", JOptionPane.PLAIN_MESSAGE);
            changeField(new_name, user_name);
        }

        if (e.getSource() == this.edit_phone){
            String new_phone = (String)JOptionPane.showInputDialog(this, "Enter a new phone number:", "PHONE CHANGE", JOptionPane.PLAIN_MESSAGE);
            changeField(new_phone, user_phone);
        }

        if (e.getSource() == this.edit_address){
            String new_address = (String)JOptionPane.showInputDialog(this, "Enter a new address:", "ADDRESS CHANGE", JOptionPane.PLAIN_MESSAGE);
            changeField(new_address, user_address);
        }

        if (e.getSource() == this.edit_pass){
            String current_pass = (String)JOptionPane.showInputDialog(this, "Enter your current password:", "PASSWORD CHANGE (1)", JOptionPane.PLAIN_MESSAGE);

            if (current_pass.equals(this.acc_user.getPassword())){
                String new_pass = (String)JOptionPane.showInputDialog(this, "Enter your new password:", "PASSWORD CHANGE (2)", JOptionPane.PLAIN_MESSAGE);
                String new_confirm_pass = (String)JOptionPane.showInputDialog(this, "Re-type your new password:", "PASSWORD CHANGE (3)", JOptionPane.PLAIN_MESSAGE);

                if (new_pass.equals(new_confirm_pass)){
                    changeField(new_confirm_pass, user_pass);  
                } else {
                    JOptionPane.showMessageDialog(this, "The new password did not match what you typed, try again.", "Oops!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Password did not match.", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
