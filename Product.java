/**
 * An immutable representation of a product.
 */
public class Product {
    public final String name;
    public final Price price;

    /**
     * Constructor
     * @param name name of the product.
     * @param price price of the product.
     */
    public Product(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " $" + price;
    }
}
