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
     * @param other The other Price to add.
     * @return A new instance with the sum of the two Prices.
     */
    public Price Add(Price other) {
        // Sum the dollars and cents
        int newDollars = this.dollars + other.dollars;
        int newCents = this.cents + other.cents;
        
        // Extract a dollar from cents.
        if (newCents >= 100) {
            newCents %= 100;
            ++newDollars;
        }

        return new Price(newDollars, newCents);
    }

    @Override 
    public String toString() {
        return dollars + "." + ((cents > 9) ? cents : ("0" + cents));
    }
}
