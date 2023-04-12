import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;


// Class to handle all GUI interactions, note this class implements the actionlistener class
public class GUI implements ActionListener{
    private JFrame window;
    private CardLayout pageManager;
    private JPanel pageLoadout;
    private LoginPage login;
    private ShoppingPage shop;
    private RegisterPage register;
    private Database db;
    private ShopResults shop_results;
    private ShopperDetails shopper_profile;
    private CartPage shopper_cart;
    private WishlistPage shopper_wish;
    private CheckoutPage shopper_checkout;
    private OrderPage shopper_orders;

    private Shopper sessionShopper;
    private Seller sessionSeller;
    private Admin seessionAdmin;

    // buttons on login page
    private JButton login_button;
    private JButton login_register_button; // register button on the login page

    // buttons on register page
    private JButton register_button;
    private JButton register_back_button; // back button on the register page

    // buttons/components on shopping page
    private JButton shop_search_button;
    private JButton shop_account_button;
    private JButton shop_cart_button;
    private JButton shop_wishlist_button;
    private JButton shop_orders_button;
    private JButton shop_logout_button;

    // buttons/components on shop results page
    private JButton shop_result_back_button;
    private JButton shop_result_review_button;

    // buttons/components on the account page
    private JButton shopper_acc_back_button;

    // buttons/components on the cart page
    private JButton shopper_cart_back_button;
    private JButton shopper_cart_checkout_button;

    // buttons/components on the checkout page
    private JButton shopper_checkout_back_button;
    private JButton shopper_checkout_place_order;

    // buttons/components on the wishlist page
    private JButton shopper_wish_back_button;

    // buttons/components on the orders page
    private JButton shopper_orders_back_button;
    
    public GUI(){
        // intialize an instance of the database
        db = new Database("./database");

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

        //grab all the buttons on the login page
        login_button = login.getLoginButton();
        login_register_button = login.getRegistrationButton();

        //grab all the buttons on the register page
        register_button = register.getRegisterButton();
        register_back_button = register.getBackButton();

        //grab all the buttons on the shopping homepage
        shop_logout_button = shop.getLogoutButton();
        shop_search_button = shop.getSearchButton();
        shop_account_button = shop.getAccButton();
        shop_cart_button = shop.getCartButton();
        shop_wishlist_button = shop.getWishlistButton();
        shop_orders_button = shop.getOrdersButton();

        // ADD ALL NECESSARY BUTTONS TO ACTION LISTENER...
        // a built-in class/interface that listens for an action to take place, which we can manipulate into taking a specific action

        // login page
        login_button.addActionListener(this);
        login_register_button.addActionListener(this);

        // register page
        register_button.addActionListener(this);
        register_back_button.addActionListener(this);

        // shopping page
        shop_logout_button.addActionListener(this);
        shop_search_button.addActionListener(this);
        shop_account_button.addActionListener(this);
        shop_cart_button.addActionListener(this);
        shop_wishlist_button.addActionListener(this);
        shop_orders_button.addActionListener(this);
        
        // display the window
        window.setVisible(true);
    }

    // since the action listener is an interface, we can write our own implementation to switch to different pages when clicking a button
    @Override
    public void actionPerformed(ActionEvent e){
        // LOGIN PAGE INTERACTIONS
        // if we click the login button, switch to the shopping page
        if (e.getSource() == login_button){
            try {
                // checks if the fields, corresponds to a current user in the database
                String userID = db.authenticateUsers(login.getEmail(), login.getPass(), login.getSelection());

                if (userID != null) {
                    // grabs the user's information
                    String userInfoLine = db.fromUsers(Integer.parseInt(userID));

                    Map<String, String> userMap = db.lineToMap(userInfoLine);
                    
                    sessionShopper = db.mapToShopper(userMap);

                    if (login.getSelection() == "Shopper"){
                        showShop();
                    }
                }
                else{
                    // displays error message notifying user if something went wrong
                    JOptionPane.showMessageDialog(login, "invalid login, please check your login details or consider registering for an account.", "Oops!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException login_e) {
                // TODO Auto-generated catch block
                login_e.printStackTrace();
            }
        }

        // REGISTER PAGE INTERACTIONS
        // if we click on the register button on the login page, switch to the registration page
        if (e.getSource() == login_register_button){
            showRegister();
        }
        
        // if we decide to click the back button, we simply re-show the login page
        if (e.getSource() == register_back_button){
            showLogin();
        }

        // if we click on the register button on the registration page, update the user database and enter a new session
        if (e.getSource() == register_button){
            try {
                if (db.authenticateUsers(register.getEmail(), register.getPass(), register.getSelection()) == null && !register.getEmail().equals("") && !register.getPass().equals("")){
                    if (register.getSelection() == "Shopper"){
                        // adds the shopper to the database
                        Shopper s = new Shopper("", "", register.getPass(), register.getEmail());
                        db.writeUsers(s);
                        
                        // updates the user info for the current session
                        sessionShopper = s;
                        showShop();
                    }
                }else {
                    JOptionPane.showMessageDialog(register, "You may have not entered in your details in correctly or there might already an email under this account, please try again.", "Oops!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException register_e) {
                register_e.printStackTrace();
            }
        }
        
        // SHOPPING PAGE INTERACTIONS
        if (e.getSource() == shop_logout_button){
            showLogin();
        }

        // if we click on the search button
        if (e.getSource() == shop_search_button){
            if (shop.getQueryString().equals("")){
                JOptionPane.showMessageDialog(register, "Please enter a key word or search term in the search bar to continue.", "Oops!", JOptionPane.ERROR_MESSAGE);
            } else{
                showResultPage(shop.getQueryString());
            }
        }

        // if we click on the account button
        if (e.getSource() == shop_account_button){
            showShopper(sessionShopper);
        }

        // if we click on the view cart button
        if (e.getSource() == shop_cart_button){
            showShopCart(sessionShopper);
        }

        // if we click on the view wishlist button
        if (e.getSource() == shop_wishlist_button){
            showShopWish(sessionShopper);
        }

        // if we click on the view orders button
        if (e.getSource() == shop_orders_button){
            showShopOrders(sessionShopper);
        }

        // SHOP RESULTS PAGE INTERACTIONS
        // if we want to go back to the shopping home page
        if (e.getSource() == shop_result_back_button){
            showShop();
        }

        // SHOPPER PROFILE INTERACTIONS
        if (e.getSource() == shopper_acc_back_button){
            showShop();
        }

        // SHOPPER CART PAGE INTERACTIONS
        if (e.getSource() == shopper_cart_back_button){
            showShop();
        }

        if (e.getSource() == shopper_cart_checkout_button){
            showShopCheckout(sessionShopper);
        }

        // SHOPPER CHECKOUT PAGE INTERACTIONS

        if (e.getSource() == shopper_checkout_back_button){
            showShopCart(sessionShopper);
        }

        if (e.getSource() == shopper_checkout_place_order){
            if (shopper_checkout.allFieldsEntered()){
                try {
                    // create the order, and update the user
                    db.writeOrders(this.sessionShopper.placeOrder());
                    db.deleteUser(this.sessionShopper.getId());
                    db.writeUsers(this.sessionShopper);
                    showShop();
                } catch (IOException err) {
                    err.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(shopper_checkout, "Cannot place order, you have an empty field.", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
        }

        // SHOPPER WISHLIST PAGE INTERACTIONS
        if (e.getSource() == shopper_wish_back_button){
            showShop();
        }

        // SHOPPER ORDER PAGE INTERACTIONS
        if (e.getSource() == shopper_orders_back_button){
            showShop();
        }
    }

    public GUI getSelf(){
        return this;
    }

    // switches to the login screen
    public void showLogin(){
        pageManager.show(pageLoadout, "login");
    }

    // switches to register screen
    public void showRegister(){
        pageManager.show(pageLoadout, "register");
    }

    // switches to the shopping home screen
    public void showShop(){
        pageManager.show(pageLoadout, "shop");
    }

    public void showShopCart(Shopper s){
        shopper_cart = new CartPage(db, s);
        pageLoadout.add(shopper_cart, "shopper cart");

        shopper_cart_back_button = shopper_cart.getBackButton();
        shopper_cart_back_button.addActionListener(this);

        shopper_cart_checkout_button = shopper_cart.getCheckoutButton();
        shopper_cart_checkout_button.addActionListener(this);

        shopper_cart.pageInit();

        pageManager.show(pageLoadout, "shopper cart");
    }

    public void showShopCheckout(Shopper s){
        shopper_checkout = new CheckoutPage(db, s);
        pageLoadout.add(shopper_checkout, "checkout");

        shopper_checkout_back_button = shopper_checkout.getBackButton();
        shopper_checkout_back_button.addActionListener(this);

        shopper_checkout_place_order = shopper_checkout.getOrderButton();
        shopper_checkout_place_order.addActionListener(this);

        shopper_checkout.pageInit();

        pageManager.show(pageLoadout, "checkout");
    }

    public void showShopWish(Shopper s){
        shopper_wish = new WishlistPage(db, s);
        pageLoadout.add(shopper_wish, "shopper wishlist");

        shopper_wish_back_button = shopper_wish.getBackButton();
        shopper_wish_back_button.addActionListener(this);

        shopper_wish.pageInit();

        pageManager.show(pageLoadout, "shopper wishlist");
    }

    public void showShopOrders(Shopper s){
        shopper_orders = new OrderPage(db, s);
        pageLoadout.add(shopper_orders, "shopper orders");
        
        shopper_orders.pageInit();

        shopper_orders_back_button = shopper_orders.getBackButton();
        shopper_orders_back_button.addActionListener(this);

        pageManager.show(pageLoadout, "shopper orders");
    }

    // switches to shopper profile screen
    public void showShopper(Shopper s){
        shopper_profile = new ShopperDetails(db, s);
        pageLoadout.add(shopper_profile, "shopper profile");

        shopper_profile.pageInit();

        shopper_acc_back_button = shopper_profile.getBackButton();
        shopper_acc_back_button.addActionListener(this);

        pageManager.show(pageLoadout, "shopper profile");
    }

    public void showResultPage(String query){
        try {
            shop_results = new ShopResults(db, db.searchProducts(query), sessionShopper);
            pageLoadout.add(shop_results, "shop results");

            shop_results.pageInit();

            // grab buttons from shop result page
            shop_result_back_button = shop_results.getBackButton();
            shop_result_back_button.addActionListener(this);

            shop_result_review_button = shop_results.getReviewButton();
            shop_result_review_button.addActionListener(this);

            pageManager.show(pageLoadout, "shop results");
        } catch (FileNotFoundException res_e) {
            // TODO Auto-generated catch block
            res_e.printStackTrace();
        }
    }
}
