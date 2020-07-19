package icfpc2020.galaxy;

import icfpc2020.Command;

import java.math.BigInteger;

public class Vect extends Expr {
    BigInteger x;
    BigInteger y;

    Vect(final BigInteger x, final BigInteger y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

}
