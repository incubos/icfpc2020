package icfpc2020.galaxy;

import java.math.BigInteger;

public class Vect extends Expr {
    BigInteger X;
    BigInteger Y;

    Vect(final BigInteger x, final BigInteger y) {
        this.X = x;
        this.Y = y;
    }

    @Override
    public String toString() {
        return X + ", " + Y;
    }

}
