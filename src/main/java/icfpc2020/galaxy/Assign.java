package icfpc2020.galaxy;

public class Assign extends Expr {
    Atom var;
    Expr Expr;

    Assign(final Atom left, final Expr ext) {
        this.var = left;
        this.Expr = ext;
    }

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder();
        if (var != null) {
            b.append(var.toString());
        }
        if (Expr != null) {
            b.append(" = ");
            b.append(Expr.toString());
        }

        return b.toString();
    }

}
