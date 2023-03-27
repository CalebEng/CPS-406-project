package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Class to handle all GUI interactions, note this class implements the actionlistener class
public class GUI implements ActionListener{
    private JFrame window;
    private CardLayout pageManager;
    private JPanel pageLoadout;
    private LoginPage login;
    private ShoppingPage shop;
    private JButton login_button;
    

    public GUI(){
        // set a base frame/canvas to load/unload all the pages
        window = new JFrame("GNRC SHOE STORE");
        window.setSize(1280, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setup the page manager to switch between pages
        pageManager = new CardLayout();
        pageLoadout = new JPanel(pageManager);

        // load default screen (login)
        login = new LoginPage();
        shop = new ShoppingPage();

        // add the pages to the loadout
        pageLoadout.add(login, "login");
        pageLoadout.add(shop, "shop");

        // add the loadout to the screen
        window.add(pageLoadout);

        // load the components of the login page
        login.pageInit();
        shop.pageInit();

        //grab the login button component
        login_button = login.geButton();
        // add it to the action listener, which listens for an action to take place in order to do something
        login_button.addActionListener(this);
        
        // set default behaviour/size
        window.setVisible(true);
    }

    // since the action listener is an interface, we can write our own implementation to switch to different pages when clicking a button
    @Override
    public void actionPerformed(ActionEvent e){
        // if we click the login button
        if (e.getSource() == login_button){
            showShop();
        }
    }

    // switches to the login screen
    public void showLogin(){
        pageManager.show(pageLoadout, "login");
    }

    // switches to the shopping screen
    public void showShop(){
        pageManager.show(pageLoadout, "shop");
    }
}
