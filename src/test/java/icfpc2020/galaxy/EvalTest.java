package icfpc2020.galaxy;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

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

}
