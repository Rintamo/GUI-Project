public class ProductEntry {
    public final Product product;
    public final int quantity;

    public ProductEntry(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return product + " Qty: " + quantity;
    }
}
