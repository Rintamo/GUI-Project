import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*; 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Gui implements ActionListener {

static JFrame AddWindow = new JFrame();
static JFrame Test;
static JPanel Buttons;
static JTable Info;
JButton Add;
JButton Return;
JButton ResetCart;
JButton Checkout;


    public Gui(){
        Test();
    }

    public void Test(){
        Test = new JFrame();
        Test.setSize(1600,1000);
        Test.setTitle("Shopping Cart");
        Test.setLayout(new BorderLayout());;
        
        Test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel Container1 = new JPanel();
        JPanel ProductName = new JPanel();
        JPanel Price = new JPanel();
        JPanel quantity = new JPanel();

        Container1.setLayout(new GridLayout(1,3));
        ProductName.add(new JLabel("Product"));
        Price.add(new JLabel("Price"));
        quantity.add(new JLabel("Quantity"));
        ProductName.setLayout(new GridLayout(0, 1));
        Price.setLayout(new GridLayout(0,1));
        quantity.setLayout(new GridLayout(0,1));

        Container1.add(ProductName);
        Container1.add(Price);
        Container1.add(quantity);

    
        ProductList Inventory = ShoppingCart.CreateInventory();
        ArrayList<ProductEntry> productEntries = Inventory.GetProductEntries();
        //DefaultListModel<String> listModel = new DefaultListModel<>();
        //JList<String> list = new JList<>(listModel);
        String Column[]= {"Product","Price","#"};
        DefaultTableModel tableModel = new DefaultTableModel(Column, 0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Info = new JTable(tableModel);

        for (ProductEntry productEntry : productEntries){
            Product product = productEntry.product;
            String labelText = product.name;
            String labelPrice = product.price.toString();

            JLabel PriceLabels = new JLabel(product.price.toString());
            JLabel productLabels = new JLabel(product.name);

            String quantityString = Integer.toString(productEntry.quantity);
            JLabel quantityLabel = new JLabel(quantityString);

           // listModel.addElement(labelText);
            Price.add(PriceLabels);
            ProductName.add(productLabels);
            quantity.add(quantityLabel);

            Object[] data ={labelText , labelPrice , quantityString};
            tableModel.addRow(data);
            
            }
        Info.setModel(tableModel);
        Info.setBounds(30, 40, 300, 300);
        Info.setSize(400, 400);
       // list.setModel(listModel);
        JScrollPane scroller = new JScrollPane(Info);
        //scroller.setViewportView(list);
        //list.setLayoutOrientation(JList.VERTICAL);
        //Test.add(list);
        Test.add(scroller,BorderLayout.WEST);
        Container1.setSize(300,200);
        

        Buttons = new JPanel();
        Buttons.setVisible(true);
        Buttons.setLayout(new GridBagLayout());
        Buttons.setSize(150,100); 

        GridBagConstraints B = new GridBagConstraints();
        
        Add = new JButton("Add >");
        Return = new JButton("< Return");
        ResetCart = new JButton("ResetCart");
        Checkout = new JButton("Checkout");
        
        Dimension buttonSize = new Dimension(250, 100); // Adjust the dimensions as needed
        Add.setPreferredSize(buttonSize);
        Return.setPreferredSize(buttonSize);
        ResetCart.setPreferredSize(buttonSize);
        Checkout.setPreferredSize(buttonSize);

        B.gridx = 0;
        B.gridy = 0;
        Buttons.add(Add,B);

        B.insets = new Insets(10, 0, 0, 0);
        B.gridx = 0;
        B.gridy = 1;
        Buttons.add(Return, B);

        B.insets = new Insets(10, 0, 0, 0);
        B.gridx = 0;
        B.gridy = 2;
        Buttons.add(ResetCart, B);
         
        B.insets = new Insets(10, 0, 0, 0);
        B.gridx = 0;
        B.gridy = 3;
        Buttons.add(Checkout, B);

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                AddToCart();
            }
        });
        
        //AFO.setLayout(new BoxLayout(AFO, BoxLayout.X_AXIS));
        //Test.add(Container , BorderLayout.WEST);
        Test.add(Buttons , BorderLayout.CENTER);

        //JPanel Container2 = new JPanel();
        DefaultTableModel TableAdd = new DefaultTableModel(Column,0);
        JTable Empty =  new JTable(TableAdd);
        Empty.setModel(TableAdd);
        Empty.setBounds(30, 40, 300, 300);
        Info.setSize(400, 400);
        JScrollPane Results = new JScrollPane(Empty);
       // Container2.add(Results);
       //  Container2.setSize(900, 300);
       // Container2.setPreferredSize(new Dimension(500, 300));
        Test.add(Results , BorderLayout.EAST);
        Test.setVisible(true);
    }
    public JFrame AddToCart(){

        AddWindow.setTitle("Add to Cart");
        
        AddWindow.getContentPane().removeAll();
        AddWindow.setLayout(new GridBagLayout());
        AddWindow.setSize(400,200);
        
        GridBagConstraints A = new GridBagConstraints();
        JLabel Text1 = new JLabel();
        Text1.setText("Add How Many?" + " " + " of #");
        A.gridwidth = 3;
        A.gridy = 0;
        AddWindow.add(Text1 , A);

        JButton Cancel = new JButton("Cancel");
        A.fill = GridBagConstraints.HORIZONTAL;
        A.gridx = 0;
        A.gridy = 1;
        A.ipadx = 30;
        A.insets = new Insets(10, 10, 10, 10);
        AddWindow.add(Cancel , A);

        JButton AddAll = new JButton("Add All");
        A = new GridBagConstraints();
        A.gridx = 1;
        A.gridy = 1;
        A.insets = new Insets(10, 10, 10, 10);
        AddWindow.add(AddAll, A);

        JButton AddOne = new JButton("Add");
        A = new GridBagConstraints();
        A.gridx = 2;
        A.gridy = 1;
        A.insets = new Insets(10, 10, 10, 10); 
        AddWindow.add(AddOne, A);
        AddWindow.setVisible(true);
        return AddWindow;
    }
    public static void main(String[] args) {
        new Gui();
    
    }
    MouseListener mouse = new MouseAdapter() {
    public void mouseClicked(MouseEvent e){
         if (e.getClickCount() == 2 ){
            int index = Info.getSelectedRow();
            System.out.println(index + "Is Selected");
         }
    }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        ShoppingCart Reset = new ShoppingCart();
        Reset.ResetCart();
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
