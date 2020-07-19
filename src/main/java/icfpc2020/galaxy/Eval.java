package icfpc2020.galaxy;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

// Adopted from https://message-from-space.readthedocs.io/en/latest/implementation.html
public class Eval {

    public final static Expr cons = new Atom("cons");
    public final static Expr t = new Atom("t");
    public final static Expr f = new Atom("f");
    public final static Expr nil = new Atom("nil");


    // See https://message-from-space.readthedocs.io/en/latest/message39.html
    private Expr state = nil;
    private Vect vector = new Vect(BigInteger.ZERO, BigInteger.ZERO);

    public void iterate() {
        while (true) {
            Expr click = new Ap(new Ap(cons, new Atom(vector.X)), new Atom(vector.Y));
            final Expr[] res = interact(state, click);
            final Expr newState = res[0];
            final Expr images = res[1];
            PRINT_IMAGES(images);
            vector = REQUEST_CLICK_FROM_USER();
            state = newState;
        }
    }

    private Vect REQUEST_CLICK_FROM_USER() {
        return null;
    }

    private void PRINT_IMAGES(Expr images) {

    }

    // flag, newState, data
    class FlagStateData {
        public Expr flag;
        public Expr newState;
        public Expr data;
        public FlagStateData(Expr flag, Expr newState, Expr data) {
            this.flag = flag;
            this.newState = newState;
            this.data = data;
        }
    }
    private FlagStateData GET_LIST_ITEMS_FROM_EXPR(Expr res) {
        throw new UnsupportedOperationException();
    }

    private Expr SEND_TO_ALIEN_PROXY(Expr data) {
        throw new UnsupportedOperationException();
    }


    // See https://message-from-space.readthedocs.io/en/latest/message38.html
    // 2 exprs
    public Expr[] interact(Expr state, Expr event) {
        Expr expr = new Ap(new Ap(new Atom("galaxy"), state), event);
        Expr res = eval(expr);
        // Note: res will be modulatable here (consists of cons, nil and numbers only)
        final FlagStateData fsd =  GET_LIST_ITEMS_FROM_EXPR(res);
        if (asNum(fsd.flag).equals(BigInteger.ZERO)) {
            return new Expr[] {fsd.newState, fsd.data};
        }
        return interact(fsd.newState, SEND_TO_ALIEN_PROXY(fsd.data));
    }

    public final Map<String, Expr> functions = new HashMap<>();

    public Expr eval(Expr expr) {
        if (expr.Evaluated != null) {
            return expr.Evaluated;
        }
        Expr initialExpr = expr;
        while (true) {
            Expr result = tryEval(expr);
            if (result == expr) {
                initialExpr.Evaluated = result;
                return result;
            }
            expr = result;
        }
    }

    private Expr tryEval(Expr expr) {
        if (expr.Evaluated != null)
            return expr.Evaluated;
        if (expr instanceof Atom && functions.containsKey(((Atom)expr).Name)) {
            return functions.get(((Atom)expr).Name);
        }
        if (expr instanceof Ap) {
            Expr fun = eval(((Ap)expr).Fun);
            Expr x = ((Ap)expr).Arg;
            if (fun instanceof Atom) {
                if (((Atom) fun).Name.equals("neg")) return new Atom(asNum(eval(x)).negate());
                if (((Atom) fun).Name.equals("i")) return x;
                if (((Atom) fun).Name.equals("nil")) return t;
                if (((Atom) fun).Name.equals("isnil")) return new Ap(x, new Ap(t, new Ap(t, f)));
                if (((Atom) fun).Name.equals("car")) return new Ap(x, t);
                if (((Atom) fun).Name.equals("cdr")) return new Ap(x, f);
                // Added, but not used in galaxy.
                if (((Atom) fun).Name.equals("inc")) return new Atom(asNum(eval(x)).add(BigInteger.ONE));
                if (((Atom) fun).Name.equals("dec")) return new Atom(asNum(eval(x)).subtract(BigInteger.ONE));
            }

            if (fun instanceof Ap) {
                Expr fun2 = eval(((Ap)fun).Fun);
                Expr y = ((Ap)fun).Arg;
                if (fun2 instanceof Atom) {
                    if (((Atom) fun2).Name.equals("t")) return y;
                    if (((Atom) fun2).Name.equals("f")) return x;
                    if (((Atom) fun2).Name.equals("add")) return new Atom(asNum(eval(x)).add(asNum(eval(y))));
                    if (((Atom) fun2).Name.equals("mul")) return new Atom(asNum(eval(x)).multiply(asNum(eval(y))));
                    if (((Atom) fun2).Name.equals("div")) return new Atom(asNum(eval(y)).divide(asNum(eval(x))));
                    if (((Atom) fun2).Name.equals("lt")) return asNum(eval(y)).compareTo(asNum(eval(x))) < 1 ? t : f;
                    if (((Atom) fun2).Name.equals("eq")) return asNum(eval(x)).equals(asNum(eval(y))) ? t : f;
                    if (((Atom) fun2).Name.equals("cons")) return evalCons(y, x);
                }

                if (fun2 instanceof Ap) {
                    Expr fun3 = eval(((Ap)fun2).Fun);
                    Expr z = ((Ap)fun2).Arg;
                    if (fun3 instanceof Atom) {
                        if (((Atom) fun3).Name.equals("s")) return new Ap(new Ap(z, x), new Ap(y, x));
                        if (((Atom) fun3).Name.equals("c")) return new Ap(new Ap(z, x), y);
                        if (((Atom) fun3).Name.equals("b")) return new Ap(z, new Ap(y, x));
                        if (((Atom) fun3).Name.equals("cons")) return new Ap(new Ap(x, z), y);
                    };
                }
            }
        }
        return expr;
    }

    private Expr evalCons(Expr a, Expr b) {
        Expr res = new Ap(new Ap(cons, eval(a)), eval(b));
        res.Evaluated = res;
        return res;
    }

    private BigInteger asNum(Expr n) {
        if (n instanceof Atom) {
            return new BigInteger(((Atom) n).Name);
        }
        throw new IllegalStateException("Not a number " + n.toString());
    }

}
