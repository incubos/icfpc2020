package icfpc2020.galaxy;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class EvalTest {
    private static String compute(final String program, final String variable) throws IOException {
        final GalaxyParser galaxyParser = new GalaxyParser();
        final Eval eval = new Eval();
        for (final String line: program.split("\n")) {
            final Assign assign = galaxyParser.parseTextLine(line);
            eval.functions.put(assign.var.Name, assign.Expr);
        }
        return eval.eval(eval.functions.get(variable)).toString();
    }

    @Test
    public void testSimple() throws IOException {
        Assert.assertEquals("1", compute("test = 1", "test"));
    }

    @Test
    public void testApInc() throws IOException {
        Assert.assertEquals("2", compute("test = ap inc 1", "test"));
    }


    @Test
    public void testApDec() throws IOException {
        Assert.assertEquals("0", compute("test = ap dec 1", "test"));
    }


    @Test
    public void testAdd() throws IOException {
        Assert.assertEquals("3", compute("test = ap ap add 1 2", "test"));
    }

    @Test
    public void testVars() throws IOException {
        Assert.assertEquals("3",
                compute("x0 = 1\n" +
                        "x1 = 2\n" +
                        "x2 = ap ap add x0 x1", "x2"));
    }

    @Test
    public void testVarsForward() throws IOException {
        Assert.assertEquals("42",
                compute("test = ap dec x0\n" +
                        "x0 = x1\n" +
                        "x1 = ap inc 42", "test"));
    }

    @Test
    public void testCurry() throws IOException {
        Assert.assertEquals("15",
                compute("x0 = ap add 10\n" +
                        "x1 = ap x0 5", "x1"));
    }

    @Test
    public void testCurryInner() throws IOException {
        Assert.assertEquals("3",
                compute("x0 = 1\n" +
                        "x1 = 2\n" +
                        "test = ap ap add x0 x1", "test"));
    }

    @Test
    public void testLt() throws IOException {
        Assert.assertEquals("f", compute("test = ap ap lt 0 -1", "test"));
    }

    @Test
    public void testCar() throws IOException {
        Assert.assertEquals("ap x2 t", compute("x2 = 2\n" +
                        "test = ap car x2", "test"));
    }


    @Test
    public void testEq() throws IOException {
        Assert.assertEquals("f", compute("test = ap ap eq 0 20", "test"));
    }

    @Test
    public void testId() throws IOException {
        Assert.assertEquals("5", compute("test = ap i 5", "test"));
    }

    @Test
    public void testS() throws IOException {
        Assert.assertEquals("3", compute("test = ap ap ap s add inc 1", "test"));
        Assert.assertEquals("42", compute("test = ap ap ap s mul ap add 1 6 ", "test"));
    }

    @Test
    public void evalGalaxy() throws IOException {
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
        System.err.println(galaxy);
        Assert.assertNotNull(galaxy.toString());
    }


    @Test
    public void testListOfPairs() {
// ( )   =   nil
//( x0 )   =   ap ap cons x0 nil
//( x0 , x1 )   =   ap ap cons x0 ap ap cons x1 nil
//( x0 , x1 , x2 )   =   ap ap cons x0 ap ap cons x1 ap ap cons x2 nil
//( x0 , x1 , x2 , x5 )   =   ap ap cons x0 ap ap cons x1 ap ap cons x2 ap ap cons x5 nil
        final Eval eval = new Eval();
        Assert.assertEquals("nil",
                eval.listOfVectorsExpr(List.of()).toString());
        Assert.assertEquals("ap ap cons ap ap cons 0 1 nil",
                eval.listOfVectorsExpr(List.of(new Vect(0, 1))).toString());
        Assert.assertEquals("ap ap cons ap ap cons 1 2 ap ap cons ap ap cons 3 4 ap ap cons ap ap cons 5 6 nil",
                eval.listOfVectorsExpr(List.of(new Vect(1, 2), new Vect(3, 4), new Vect(5, 6))).toString());

    }


    @Test
    public void testConsumeList() {
// ( )   =   nil
//( x0 )   =   ap ap cons x0 nil
//( x0 , x1 )   =   ap ap cons x0 ap ap cons x1 nil
//( x0 , x1 , x2 )   =   ap ap cons x0 ap ap cons x1 ap ap cons x2 nil
//( x0 , x1 , x2 , x5 )   =   ap ap cons x0 ap ap cons x1 ap ap cons x2 ap ap cons x5 nil
        final StringBuilder result = new StringBuilder();
        final Eval eval = new Eval();
        Eval.consumeListOfVectors(
                eval.listOfVectorsExpr(List.of(new Vect(1, 2), new Vect(3, 4), new Vect(5, 6))),
                v -> result.append(v.X).append(",").append(v.Y).append(";")
        );
        Assert.assertEquals("1,2;3,4;5,6;", result.toString());

    }

    @Test
    public void testEvalCoordsList() {
        final Eval eval = new Eval();
        final Expr coordsList = eval.listOfVectorsExpr(List.of(new Vect(1, 2), new Vect(3, 4), new Vect(5, 6)));
        eval.eval(coordsList);
        // NO CONS evaluation in Galaxy evaluation
        Assert.assertEquals("ap ap cons ap ap cons 1 2 ap ap cons ap ap cons 3 4 ap ap cons ap ap cons 5 6 nil",
                coordsList.toString());
    }

}
