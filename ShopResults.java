import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;



// the page that shows up after you search for something, contains an instance of the ProductPage class
public class ShopResults extends JPanel implements ListSelectionListener{
    private ArrayList<Shoe> results;
    private Shoe[] result_list;
    private JButton back_button;
    private JList product_list;
    private DefaultListModel<Shoe> product_ListModel; // a built-in class that allows us to manipulate our Jist
    private JScrollPane product_list_scroll;

    public ShopResults(ArrayList<Shoe> shoes){
        this.results = shoes;
        result_list = results.toArray(new Shoe[0]); // converting the arraylist of products into a regular list for the JList

        // set up list
        product_ListModel = new DefaultListModel<Shoe>();

        for (Shoe s: result_list){
            product_ListModel.addElement(s);
        }

        product_list = new JList<Shoe>(product_ListModel);
        product_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        product_list.setSelectedIndex(0);
        product_list.addListSelectionListener(this);
        product_list_scroll = new JScrollPane(product_list); // add the ability to scroll
    }

    public void pageInit(){
        setLayout(new BorderLayout());
        add(product_list_scroll, BorderLayout.WEST);
    }

    public JButton getBackButton(){
        return this.back_button;
    }

    @Override
    public void valueChanged(ListSelectionEvent e){
        //Shoe selected_shoe = (Shoe) result_list.get
    }
}
