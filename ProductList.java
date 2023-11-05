import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a ProductList that's a Set of unique Products.
 */
public class ProductList {
    // Collarates the set of Products to an Integer representing quantity.
    HashMap<Product, Integer> products;

    /**
     * Construcor
     */
    public ProductList() {
        products = new HashMap<Product, Integer>();
    }

    /**
     * Adds Product to the list, will add a new entry to the Set if Product isn't already present.
     * @param product The Product to be added.
     * @param quantity How much of Product to be added.
     * @throws InvalidParameterException product cannot be null.
     * @throws InvalidParameterException quantity cannot be < 0.
     */
    public void AddProduct(Product product, int quantity) throws InvalidParameterException {
        if (product == null)
            throw new InvalidParameterException("product cannot be null");
        if (quantity < 0)
            throw new InvalidParameterException("quantity cannot be < 0");
         
        // Update the quantity if product is already is in set.
        if (products.containsKey(product))
            quantity += products.get(product);

        // Update/Add Product with quantity.
        products.put(product, quantity);
    }

    /**
     * Takes Product from the list.
     * @param product The Product to be added.
     * @param quantity How much of Product to be added.
     * @return true if successful, false if product isn't present or not enough quantity to fulfill request.
     * @throws InvalidParameterException product cannot be null.
     * @throws InvalidParameterException quantity cannot be < 0.
     */
    public boolean TakeProduct(Product product, int quantity) throws InvalidParameterException {
        if (product == null)
            throw new InvalidParameterException("product cannot be null");
        if (quantity < 0)
            throw new InvalidParameterException("quantity cannot be < 0");
        
        // Product is not in Set.
        if (!products.containsKey(product))
            return false;

        // Make sure not taking too much quantity.
        int prodQuantity = products.get(product) - quantity;
        if (prodQuantity < 0)
            return false;
        
        if (prodQuantity > 0)
            // Update the quantity.
            products.put(product, prodQuantity);
        else
            // Remove Product from set if none left.
            products.remove(product);

        return true;
    }

    /**
     * Generates an ArrayList of ProductEntries based off the ProductList.
     * @return An ArrayList of ProductEntries based off the ProductList.
     */
    public ArrayList<ProductEntry> GetProductEntries() {
        ArrayList<ProductEntry> productEntries = new ArrayList<ProductEntry>(products.size());
        
        for (var entry : products.entrySet())
            productEntries.add(new ProductEntry(entry.getKey(), entry.getValue()));

        return productEntries;
    }

    /**
     * Check if ProductList is empty.
     * @return true if empty, false if not.
     */
    public boolean Empty() {
        return products.isEmpty();
    }
}
