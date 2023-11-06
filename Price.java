import java.security.InvalidParameterException;

/**
 * Immutable representation of price.
 */
public class Price {
    public final int dollars;
    public final int cents;

    /**
     * Constructor
     * @param dollars How many dollars.
     * @param cents How many cents.
     * @throws InvalidParameterException dollars cannot be < 0.
     * @throws InvalidParameterException cents cannot be < 0.
     * @throws InvalidParameterException cents cannot be > 99.
     */
    public Price(int dollars, int cents) throws InvalidParameterException {
        if (dollars < 0)
            throw new InvalidParameterException("dollars cannot be < 0");
        if (cents < 0)
            throw new InvalidParameterException("cents cannot be < 0");
        if (cents > 99)
            throw new InvalidParameterException("cents cannot be > 99");

        this.dollars = dollars;
        this.cents = cents;
    }

    /**
     * Add two Prices together.
     * @param a A Price to add.
     * @param b Another Price to add.
     * @return A new instance with the sum of the two Prices.
     */
    public static Price Add(Price a, Price b) {
        // Sum the dollars and cents.
        int newDollars = Math.addExact(b.dollars, a.dollars);
        int newCents = Math.addExact(b.cents, a.cents);
        
        // Extract a dollar from cents.
        if (newCents >= 100) {
            newDollars = Math.addExact(newDollars, 1);
            newCents %= 100;
        }

        return new Price(newDollars, newCents);
    }

    /**
     * Multiplies a Price by a scalar amount.
     * @param mul Scalar to multiply by.
     * @return A new instance of the multiplied price.
     * @throws InvalidParameterException mul cannot be < 0.
     */
    public static Price Mul(Price price, int mul) throws InvalidParameterException {
        if (mul < 0)
            throw new InvalidParameterException("mul cannot be < 0");

        // Multiply the dollars and cents.
        int newDollars = Math.multiplyExact(price.dollars, mul);
        int newCents = Math.multiplyExact(price.cents, mul);
        
        // Extract dollars from cents.
        if (newCents >= 100) {
            newDollars = Math.addExact(newDollars, Math.floorDiv(newCents, 100));
            newCents %= 100;
        }

        return new Price(newDollars, newCents);
    }

    @Override 
    public String toString() {
        return dollars + "." + ((cents > 9) ? cents : ("0" + cents));
    }
}
