package icfpc2020.galaxy;

import java.math.BigInteger;

public class Vect extends Expr {
    public BigInteger X;
    public BigInteger Y;

    Vect(final BigInteger x, final BigInteger y) {
        this.X = x;
        this.Y = y;
    }

    public Vect(final int x, final int y) {
        this(BigInteger.valueOf(x), BigInteger.valueOf(y));
    }

    @Override
    public String toString() {
        return X + ", " + Y;
    }

}
