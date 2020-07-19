package icfpc2020.galaxy;

import java.math.BigInteger;

public class Atom extends Expr {
     public final String Name;

    Atom(final String name) {
        this.Name = name;
    }

    Atom(final BigInteger number) {
        this(number.toString());
    }

    @Override
    public String toString() {
        return Name;
    }

}
