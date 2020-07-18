package icfpc2020;

import icfpc2020.galaxy.GalaxyEval;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

public class GalaxyEvalTest {
    private static String compute(final String program, final String variable) throws IOException {
        return new GalaxyEval().eval(new StringReader(program), variable).get(variable).toString();
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
        Assert.assertEquals("3", compute("test = ap add 1 2", "test"));
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
        Assert.assertEquals("f()", compute("test = ap ap lt 0 -1", "test"));
    }

    @Test
    public void testEq() throws IOException {
        Assert.assertEquals("f", compute("test = ap ap eq t f", "test"));
    }


}
