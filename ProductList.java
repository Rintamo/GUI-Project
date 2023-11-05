import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductList {
    HashMap<Product, Integer> products;

    public ProductList() {
        products = new HashMap<Product, Integer>();
    }

    public void AddProduct(Product product, int quantity) throws InvalidParameterException {
        if (product == null)
            throw new InvalidParameterException("product cannot be null");
        if (quantity < 0)
            throw new InvalidParameterException("quantity cannot be < 0");
         
        if (products.containsKey(product))
            quantity += products.get(product);

        products.put(product, quantity);
    }

    public boolean TakeProduct(Product product, int quantity) throws InvalidParameterException {
        if (product == null)
            throw new InvalidParameterException("product cannot be null");
        if (quantity < 0)
            throw new InvalidParameterException("quantity cannot be < 0");
        
        if (!products.containsKey(product))
            return false;

        int prodQuantity = products.get(product) - quantity;
        if (prodQuantity < 0)
            return false;
        
        if (prodQuantity > 0)
            products.put(product, prodQuantity);
        else
            products.remove(product);

        return true;
    }

    public ArrayList<ProductEntry> GetProductEntries() {
        ArrayList<ProductEntry> productEntries = new ArrayList<ProductEntry>(products.size());
        
        for (var entry : products.entrySet())
            productEntries.add(new ProductEntry(entry.getKey(), entry.getValue()));

        return productEntries;
    }

    public boolean Empty() {
        return products.isEmpty();
    }
}
