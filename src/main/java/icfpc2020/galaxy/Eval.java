package icfpc2020.galaxy;

import icfpc2020.api.PublicAPIImpl;
import icfpc2020.Draw;
import icfpc2020.ImageRenderer;
import icfpc2020.eval.value.DemodulateValue;
import icfpc2020.operators.Modulate;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// Adopted from https://message-from-space.readthedocs.io/en/latest/implementation.html
public class Eval {

    private static final Logger log = LoggerFactory.getLogger(Eval.class);

    public final static Expr cons = new Atom("cons");
    public final static Expr t = new Atom("t");
    public final static Expr f = new Atom("f");
    public final static Expr nil = new Atom("nil");
    private String imageDir;

    public Eval() {
        imageDir = Paths.get(Eval.class.getResource("/messages/message2.png").getPath())
                .getParent().getParent().toString() + "/images";
    }


    // See https://message-from-space.readthedocs.io/en/latest/message39.html
    private Expr state = nil;
    private Vect vector = new Vect(BigInteger.ZERO, BigInteger.ZERO);

    int iteration = 0;

    final Clicker clicker = new RepeatClicker(ClickerRoundabout::new, 1600); // 400 is enough for 20x20

    public void iterate() {
        while (true) {
            System.out.println("Iteration " + iteration);
            Expr click = new Ap(new Ap(cons, new Atom(vector.X)), new Atom(vector.Y));
            final Expr[] res = interact(state, click);
            final Expr newState = res[0];
            final Expr images = res[1];
            PRINT_IMAGES(images);
            vector = REQUEST_CLICK_FROM_USER();
            state = newState;
            iteration++;
            if (iteration == 1600 * 10) {
                break;
            }
        }
    }

    public Vect REQUEST_CLICK_FROM_USER() {
        final Vect vect = clicker.nextClick();
        System.out.println("Click: " + vect.X + "," + vect.Y);
        return vect;
    }

    // images is a list of pairs, se createListOfVectors
    public void PRINT_IMAGES(Expr images) {
        final List<Draw.Coord> points = new ArrayList<>();
        final String imagePath = imageDir + "/" + iteration + ".png";
        try {
            Files.deleteIfExists(Paths.get(imagePath));
        } catch (IOException e) {
            // Ignore
        }
        consumeList(images, (image) -> {
            consumeListOfVectors(image, (v) -> points.add(Draw.Coord.of(v.X, v.Y)));
        });
        if (points.size() == 0) {
            System.err.println("Empty file: " + imagePath);
        }
        if (points.size() != 0) {
            // Creates and saves
            new ImageRenderer(imagePath, points);
        }
    }

    public Expr generateCoordList(final List<Vect> coordinates) {
        // Empty
        if (coordinates.size() == 0) {
            return nil;
        }
        final List<Expr> pairs = coordinates.stream()
                .map(v -> evalCons(new Atom(v.X), new Atom(v.Y)))
                .collect(Collectors.toList());
        // Head and emtpy tail
        // ( x0 ) = ap ap cons x0 nil
        if (coordinates.size() == 1) {
            return new Ap(new Ap(cons, pairs.get(0)), nil);
        }
        // Construction from the tail = ( ) = nil
        Expr result = nil;
        for (int i = pairs.size() - 1; i >= 0; i--) {
            final Expr head = pairs.get(i);
            result = new Ap(new Ap(cons, head), result);
        }
        return result;
    }


    public static void consumeListOfVectors(Expr expr, final Consumer<Vect> consumer) {
        consumeList(expr, head -> {
            try {
                // single pair =  ap ( ap ( cons , (ap (ap cons, 0) , 1),  nil)
                // head = ap (ap cons, 0) , 1
                final Expr x = ((Ap) ((Ap) head).Fun).Arg;
                final Expr y = ((Ap) head).Arg;
                consumer.accept(new Vect(asNum(x), asNum(y)));
            } catch (Exception e) {
                log.error("Illegal coord in consumeListOfVectors: {}", expr);
                System.err.println();
            }
        });
    }

    // Head and emtpy tail
    // ( head, tail ) = ap ap cons head tail = ap ( ap ( cons, head ) , tail )
    public static void consumeList(Expr expr, final Consumer<Expr> consumer) {
        try {
            while (!expr.toString().equals(nil.toString())) {
                final Expr ap = ((Ap) expr).Fun; // ap ( cons, head )
                final Expr head = ((Ap) ap).Arg; // head
                final Expr tail = ((Ap) expr).Arg; // tail
                consumer.accept(head);
                expr = tail;
            }
        } catch (Exception e) {
            log.error("Illegal list structure in consumeList: {}", expr, e);
            System.err.println();
        }
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
//            try {
//                consumeList(newState, innerList -> consumeList(innerList, number -> asNum(number)));
//            } catch (Exception e) {
//                throw new IllegalStateException("NewState is not list of list of ... numbers: " + newState.toString());
//            }
        }
    }

    private FlagStateData GET_LIST_ITEMS_FROM_EXPR(Expr expr) {
        // Assume that we are store there in list
        final Expr[] res = new Expr[3];
        consumeList(expr, v -> {
            if (res[0] == null) {
                res[0] = v;
            } else if (res[1] == null) {
                res[1] = v;
            } else if (res[2] == null) {
                res[2] = v;
            } else {
                throw new UnsupportedOperationException("GET_LIST_ITEMS_FROM_EXPR too many values: " + expr.toString());
            }
        });
        return new FlagStateData(res[0], res[1], res[2]);
    }

    // ap, "",
    // cons, "11",
    // nil, "00",
    public void modulateRec(@NotNull final Expr arg, final StringBuilder sb) {
        if (arg == nil) {
            sb.append("00");
        } else if (arg == cons) {
            sb.append("11");
        } else if (Pattern.matches("-?//d+", arg.toString())) {
            sb.append(Modulate.modString(asNum(arg)));
        } else if (arg instanceof Ap) {
            modulateRec(((Ap) arg).Fun, sb);
            modulateRec(((Ap) arg).Arg, sb);
        } else {
            log.error("unexpected literal while modulating {}", arg.toString());
            throw new UnsupportedOperationException("modulate argument should be modulateable");
        }
    }


    private Expr SEND_TO_ALIEN_PROXY(Expr data) {
        final StringBuilder sb = new StringBuilder();
        modulateRec(data, sb);
        final String message = sb.toString();
        System.out.println("Sending: " + message);
        final String response = PublicAPIImpl.INSTANCE.send(message);
        System.out.println("Response: " + response);
        final String demodulate = DemodulateValue.demodulate(response);
        return GalaxyParser.parseCommand(new GalaxyParser.ParseTokens(demodulate.split(" "), 0));
    }


    // See https://message-from-space.readthedocs.io/en/latest/message38.html
    // 2 exprs
    public Expr[] interact(Expr state, Expr event) {
        Expr expr = new Ap(new Ap(new Atom("galaxy"), state), event);
        Expr res = eval(expr);
        // Note: res will be modulatable here (consists of cons, nil and numbers only)
        final FlagStateData fsd = GET_LIST_ITEMS_FROM_EXPR(res);
        if (asNum(fsd.flag).equals(BigInteger.ZERO)) {
            return new Expr[]{fsd.newState, fsd.data};
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
        if (expr instanceof Atom) {
            final String name = ((Atom) expr).Name;
            if (functions.containsKey(name)) {
                return functions.get(name);
            }
            if (Set.of("statelessdraw", "interact", "multipledraw").contains(name)) {
                throw new UnsupportedOperationException(name);
            }
        }
        if (expr instanceof Ap) {
            Expr fun = eval(((Ap) expr).Fun);
            Expr x = ((Ap) expr).Arg;
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
                Expr fun2 = eval(((Ap) fun).Fun);
                Expr y = ((Ap) fun).Arg;
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
                    Expr fun3 = eval(((Ap) fun2).Fun);
                    Expr z = ((Ap) fun2).Arg;
                    if (fun3 instanceof Atom) {
                        if (((Atom) fun3).Name.equals("s")) return new Ap(new Ap(z, x), new Ap(y, x));
                        if (((Atom) fun3).Name.equals("c")) return new Ap(new Ap(z, x), y);
                        if (((Atom) fun3).Name.equals("b")) return new Ap(z, new Ap(y, x));
                        if (((Atom) fun3).Name.equals("cons")) return new Ap(new Ap(x, z), y);
                    }
                    ;
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

    private static BigInteger asNum(Expr n) {
        if (n instanceof Atom) {
            return new BigInteger(((Atom) n).Name);
        }
        throw new IllegalStateException("Not a number " + n.toString());
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader reader =
                new BufferedReader(new InputStreamReader(GalaxyParser.class.getResourceAsStream("/galaxy.txt")));
        final GalaxyParser galaxyParser = new GalaxyParser();
        final Eval eval = new Eval();

        while (true) {
            final String line = reader.readLine();
            if (line == null) {
                break;
            }
            final Assign assign = galaxyParser.parseTextLine(line);
            eval.functions.put(assign.var.Name, assign.Expr);
        }
        final Expr galaxy = eval.eval(eval.functions.get("galaxy"));
        System.out.println("Galaxy parsed: " + galaxy.toString());
        System.out.println("Iterating...");
        System.out.println("Images are here:" + eval.imageDir);
        eval.iterate();
    }
}
