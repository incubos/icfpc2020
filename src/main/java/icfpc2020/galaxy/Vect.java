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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vect)) return false;
        Vect vect = (Vect) o;
        return X.equals(vect.X) && Y.equals(vect.Y);
    }

    public Vect d(int dx, int dy) {
        return new Vect(X.add(BigInteger.valueOf(dx)), Y.add(BigInteger.valueOf(dy)));
    }

}
