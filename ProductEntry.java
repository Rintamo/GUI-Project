/**
 * Immutable representation of a Product and its quantity.
 */
public class ProductEntry {
    public final Product product;
    public final int quantity;

    /**
     * Constructor
     * @param product A Product
     * @param quantity Its quantity
     */
    public ProductEntry(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return product + " Qty: " + quantity;
    }
}
