package icfpc2020.eval;

import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author incubos
 */
public class EvaluatorTest {
    private static final String TEST_FUNCTION = "test";

    @NotNull
    private static LazyValue function(@NotNull final String code) throws Exception {
        final Evaluator evaluator =
                new Evaluator(
                        new ByteArrayInputStream(
                                (TEST_FUNCTION + " = " + code).getBytes(StandardCharsets.UTF_8)));
        return evaluator.function(TEST_FUNCTION);
    }

    private static void evalConst(
            @NotNull final String expectedResult,
            @NotNull final String code) throws Exception {
        assertEquals(
                expectedResult,
                function(code).asConst().toString());
    }

    private static void isTrue(@NotNull final String code) throws Exception {
        assertEquals(
                "t",
                function(code).eval().toString());
    }

    private static void isFalse(@NotNull final String code) throws Exception {
        assertEquals(
                "f",
                function(code).eval().toString());
    }

    @Test
    public void literal() throws Exception {
        evalConst("42", "42");
    }

    @Test
    public void inc() throws Exception {
        evalConst("1", "ap inc 0");
        evalConst("2", "ap inc 1");
        evalConst("3", "ap inc ap inc ap inc 0");
        evalConst("0", "ap inc -1");
        evalConst("-1", "ap inc -2");
    }

    @Test
    public void dec() throws Exception {
        evalConst("-1", "ap dec 0");
        evalConst("0", "ap dec 1");
        evalConst("1", "ap dec 2");
        evalConst("-2", "ap dec -1");
    }

    @Test
    public void add() throws Exception {
        evalConst("3", "ap ap add 1 2");
        evalConst("3", "ap ap add 2 1");
        evalConst("1", "ap ap add 0 1");
    }

    @Test
    public void neg() throws Exception {
        evalConst("0", "ap neg 0");
        evalConst("-1", "ap neg 1");
        evalConst("1", "ap neg -1");
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
                evaluator.function("test").asConst().toString());

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
                evaluator.function("test").asConst().toString());

    }

    @Test
    public void mul() throws Exception {
        evalConst("8", "ap ap mul 4 2");
        evalConst("-6", "ap ap mul 3 -2");
    }

    @Test
    public void div() throws Exception {
        evalConst("2", "ap ap div 4 2");
        evalConst("1", "ap ap div 4 3");
        evalConst("1", "ap ap div 4 4");
        evalConst("0", "ap ap div 4 5");
        evalConst("2", "ap ap div 4 2");
        evalConst("2", "ap ap div 5 2");
        evalConst("-3", "ap ap div 6 -2");
        evalConst("-1", "ap ap div 5 -3");
        evalConst("-1", "ap ap div -5 3");
        evalConst("1", "ap ap div -5 -3");
    }

    @Test
    public void eq() throws Exception {
        isFalse("ap ap eq 0 -2");
        isFalse("ap ap eq 0 -1");
        isTrue("ap ap eq 0 0");
        isFalse("ap ap eq 0 1");
        isFalse("ap ap eq 0 2");

        isTrue("ap ap eq -1 -1");
        isFalse("ap ap eq 1 -1");
        isFalse("ap ap eq 1 0");
        isTrue("ap ap eq 1 1");
        isFalse("ap ap eq 1 2");
    }

    @Test
    public void lt() throws Exception {
        isFalse("ap ap lt 0 -1");
        isFalse("ap ap lt 0 0");
        isTrue("ap ap lt 0 1");
        isTrue("ap ap lt 0 2");

        isFalse("ap ap lt 1 0");
        isFalse("ap ap lt 1 1");
        isTrue("ap ap lt 1 2");
        isTrue("ap ap lt 1 3");

        isFalse("ap ap lt -19 -20");
        isFalse("ap ap lt -20 -20");
        isTrue("ap ap lt -21 -20");
    }

    @Test
    public void s() throws Exception {
        evalConst("3", "ap ap ap s add inc 1");
        evalConst("42", "ap ap ap s mul ap add 1 6");
    }

    @Test
    public void pwr2() throws Exception {
        evalConst("1", "ap pwr2 0");
        evalConst("2", "ap pwr2 1");
        evalConst("256", "ap pwr2 8");
        evalConst("1267650600228229401496703205376", "ap pwr2 100");
        evalConst("2", "ap ap mul 2 ap ap ap ap eq 0 0 1 ap ap ap 1 ap mul 2 ap ap 1 pwr2 ap add -1 ap ap add -1 1");
        try {
            evalConst("1", "ap pwr2 ap dec 0");
            fail("Have to throw ArithmeticException");
        } catch (ArithmeticException e) { }
    }

}
