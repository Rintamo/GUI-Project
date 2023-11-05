import java.security.InvalidParameterException;

public class Price {
    public final int dollars;
    public final int cents;

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
