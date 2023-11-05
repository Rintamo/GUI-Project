public class Product {
    public final String name;
    public final Price price;

    public Product(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " $" + price;
    }
}
