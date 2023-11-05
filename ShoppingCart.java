import java.util.Scanner;

/**
 * Shopping Cart Application.
 */
public class ShoppingCart implements AutoCloseable {
    private ProductList inventory;
    private ProductList cart;
    private Scanner scanner;

    /**
     * Constructor
     */
    public ShoppingCart() {
        inventory = CreateInventory();
        cart = new ProductList();
        scanner = new Scanner(System.in);
    }

    /**
     * Generates the contents of the Inventory.
     * @return A ProductList containing the Inventory.
     */
    public static ProductList CreateInventory() {
        ProductList productList = new ProductList();

        productList.AddProduct(new Product("Horse", new Price(10000, 89)), 10);
        productList.AddProduct(new Product("Eggs", new Price(3, 49)), 100);
        productList.AddProduct(new Product("Xbox", new Price(499, 99)), 70);
        productList.AddProduct(new Product("Joe Biden Flavored Soup", new Price(420, 69)), 1);
        productList.AddProduct(new Product("Used Pizza Slice", new Price(0, 01)), 1);

        return productList;
    }

    /**
     * Lists the options for the User to do and maintains the App loop.
     * @return A loop control boolean.
     */
    public boolean ViewOptions() {
        System.out.println("\nAvaliable Options:");
        
        int option = 1;
        System.out.println((option++) + ") View Inventory");
        System.out.println((option++) + ") View Cart");
        if (!cart.Empty()) {
            System.out.println((option++) + ") Reset Cart");
            System.out.println((option++) + ") Checkout");
        }
        System.out.println("0) Exit\n");

        int userSelection = -1;
        do {
            System.out.print("Please Select An Option: ");
            try {
                userSelection = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
        } while (userSelection < 0 || userSelection >= option);

        switch(userSelection) {
        case 1:
            ViewInventory();
            break;
        case 2:
            ViewCart();
            break;
        case 3:
            ResetCart();
            break;
        case 4:
            Checkout();
            break;
        case 0:
            return false;
        default:
            break;
        }

        return true;
    }

    /**
     * Lists the Inventory and lets the User select Products to add to Cart.
     */
    public void ViewInventory() {
        int userSelection = -1;
        while (userSelection != 0) {
            if (inventory.Empty()) {
                System.out.println("\nInventory is empty.");
                return;
            }

            System.out.println("\nInventory:");

            var productEntries = inventory.GetProductEntries();
            for (int i = 0; i < productEntries.size(); ++i)
                System.out.println((i + 1) + ") " + productEntries.get(i));
            System.out.println("0) Exit\n");
            
            do {
                System.out.print("Please Select An Option: ");
                try {
                    userSelection = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    continue;
                }
            } while (userSelection < 0 || userSelection > productEntries.size());

            if (userSelection >= 1) {
                ProductEntry productEntry = productEntries.get(userSelection - 1);

                System.out.println();
                
                int userQuantity = -1;
                do {
                    System.out.print("Please Enter How Many \"" + productEntry.product.name + "\" You Want (0 - " + productEntry.quantity + "): ");
                    try {
                        userQuantity = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        continue;
                    }
                } while (userQuantity < 0 || userQuantity > productEntry.quantity);

                inventory.TakeProduct(productEntry.product, userQuantity);
                cart.AddProduct(productEntry.product, userQuantity);
            }
        }
    }

    /**
     * Lists the Cart and lets the User return Products to Inventory.
     */
    public void ViewCart() {
        int userSelection = -1;
        while (userSelection != 0) {
            if (cart.Empty()) {
                System.out.println("\nCart is empty.");
                return;
            }

            System.out.println("\nCart:");

            var productEntries = cart.GetProductEntries();
            for (int i = 0; i < productEntries.size(); ++i)
                System.out.println((i + 1) + ") " + productEntries.get(i));
            System.out.println("0) Exit\n");
            
            do {
                System.out.print("Please Select An Option: ");
                try {
                    userSelection = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    continue;
                }
            } while (userSelection < 0 || userSelection > productEntries.size());

            if (userSelection >= 1) {
                ProductEntry productEntry = productEntries.get(userSelection - 1);

                System.out.println();
                
                int userQuantity = -1;
                do {
                    System.out.print("Please Enter How Many \"" + productEntry.product.name + "\" You Want To Return (0 - " + productEntry.quantity + "): ");
                    try {
                        userQuantity = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        continue;
                    }
                } while (userQuantity < 0 || userQuantity > productEntry.quantity);

                cart.TakeProduct(productEntry.product, userQuantity);
                inventory.AddProduct(productEntry.product, userQuantity);
            }
        }
    }

    /**
     * Resets the Shopping Cart.
     */
    public void ResetCart() {
        // Rebuild the Inventory.
        inventory = CreateInventory();
        // Make a new empty Cart.
        cart = new ProductList();
    }

    /**
     * Checksout the User by generating a Receipt.
     */
    public void Checkout() {
        System.out.println("Thanks for shopping with us today!\n");

        Price total = new Price(0, 0);
        System.out.println("Items Purchased:");
        for (var productEntry : cart.GetProductEntries()) {
            total = total.Add(productEntry.product.price);
            System.out.println(productEntry);
        }

        System.out.println("\nTotal: $" + total);
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Shopping Cart Simulator");
        try (ShoppingCart shoppingCart = new ShoppingCart()) {
            while (shoppingCart.ViewOptions());
        }
    }
}