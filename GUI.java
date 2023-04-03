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
    private RegisterPage register;
    private Database db;

    private JButton login_button;
    private JButton register_button;
    
    public GUI(){
        // intialize an instance of the database
        db = new Database("database");

        // set a base frame/canvas to load/unload all the pages
        window = new JFrame("GNRC SHOE STORE");
        window.setSize(1280, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setup the page manager (cardlayout) which will allow us to seamlessly switch between pages
        pageManager = new CardLayout();
        pageLoadout = new JPanel(pageManager);

        // load default screen (login)
        login = new LoginPage();
        shop = new ShoppingPage();
        register = new RegisterPage();

        // add the pages to the loadout (you can think of this as like a list of pages that the page manager can swap between)
        pageLoadout.add(login, "login");
        pageLoadout.add(shop, "shop");
        pageLoadout.add(register, "register");

        // add the loadout to the screen
        window.add(pageLoadout);

        // initialize the components of the login and shopping page
        login.pageInit();
        shop.pageInit();
        register.pageInit();

        //grab the login button component
        login_button = login.getButton();
        // add it to the `action listener`, a built-in class/interface that listens for an action to take place, which we can manipulate into taking a specific action
        login_button.addActionListener(this);
        
        // display the window
        window.setVisible(true);
    }

    // since the action listener is an interface, we can write our own implementation to switch to different pages when clicking a button
    @Override
    public void actionPerformed(ActionEvent e){
        // if we click the login button, switch to the shopping page
        if (e.getSource() == login_button){
            showShop();
        }
    }

    // switches to the login screen
    public void showLogin(){
        pageManager.show(pageLoadout, "login");
    }

    // switches to register screen
    public void showRegister(){
        pageManager.show(pageLoadout, "register");
    }

    // switches to the shopping screen
    public void showShop(){
        pageManager.show(pageLoadout, "shop");
    }
}
