package icfpc2020.eval;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * @author incubos
 */
public class EvaluatorTest {
    private static void eval(
            @NotNull final String expectedResult,
            @NotNull final String code) throws Exception {
        final Evaluator evaluator =
                new Evaluator(
                        new ByteArrayInputStream(
                                code.getBytes(StandardCharsets.UTF_8)));
        assertEquals(
                expectedResult,
                evaluator.function("test").eval().asConst().toString());
    }

    @Test
    public void literal() throws Exception {
        eval("42", "test = 42");
    }

    @Test
    public void inc() throws Exception {
        eval("1", "test = ap inc 0");
        eval("2", "test = ap inc 1");
        eval("3", "test = ap inc ap inc ap inc 0");
        eval("0", "test = ap inc -1");
        eval("-1", "test = ap inc -2");
    }

    @Test
    public void dec() throws Exception {
        eval("-1", "test = ap dec 0");
        eval("0", "test = ap dec 1");
        eval("1", "test = ap dec 2");
        eval("-2", "test = ap dec -1");
    }

    @Test
    public void add() throws Exception {
        eval("3", "test = ap ap add 1 2");
        eval("3", "test = ap ap add 2 1");
        eval("1", "test = ap ap add 0 1");
    }

    @Test
    public void neg() throws Exception {
        eval("0", "test = ap neg 0");
        eval("-1", "test = ap neg 1");
        eval("1", "test = ap neg -1");
    }

    @Test
    public void variables() throws Exception {
        final String code =
                "x0 = 1\n" +
                        "x1 = 2\n" +
                        "test = ap ap add x0 x1";
        final Evaluator evaluator =
                new Evaluator(
                        new ByteArrayInputStream(
                                code.getBytes(StandardCharsets.UTF_8)));
        assertEquals(
                "3",
                evaluator.function("test").eval().asConst().toString());

    }

    @Test
    public void variablesForward() throws Exception {
        final String code =
                "test = ap dec x0\n" +
                "x0 = x1\n" +
                "x1 = ap inc 42";
        final Evaluator evaluator =
                new Evaluator(
                        new ByteArrayInputStream(
                                code.getBytes(StandardCharsets.UTF_8)));
        assertEquals(
                "42",
                evaluator.function("test").eval().asConst().toString());

    }

    @Test
    public void mul() throws Exception {
        eval("8", "test = ap ap mul 4 2");
        eval("-6", "test = ap ap mul 3 -2");
    }

    @Test
    public void div() throws Exception {
        eval("2", "test = ap ap div 4 2");
        eval("1", "test = ap ap div 4 3");
        eval("1", "test = ap ap div 4 4");
        eval("0", "test = ap ap div 4 5");
        eval("2", "test = ap ap div 4 2");
        eval("2", "test = ap ap div 5 2");
        eval("-3", "test = ap ap div 6 -2");
        eval("-1", "test = ap ap div 5 -3");
        eval("-1", "test = ap ap div -5 3");
        eval("1", "test = ap ap div -5 -3");
    }
}
