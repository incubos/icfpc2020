package icfpc2020.galaxy;

import icfpc2020.Command;

public class Ap extends Expr {
    Expr Fun;
    Expr Arg;

    Ap(final Expr fun, final Expr arg) {
        this.Fun = fun;
        this.Arg = arg;
    }

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder();
        b.append(Command.App.toString());
        if (Fun != null) {
            b.append(" ");
            b.append(Fun.toString());
        }
        if (Arg != null) {
            b.append(" ");
            b.append(Arg.toString());
        }

        return b.toString();
    }

}
