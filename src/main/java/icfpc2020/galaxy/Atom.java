package icfpc2020.galaxy;

public class Atom extends Expr {
     public final String Name;

    Atom(final String name) {
        this.Name = name;
    }

    @Override
    public String toString() {
        return Name;
    }

}
