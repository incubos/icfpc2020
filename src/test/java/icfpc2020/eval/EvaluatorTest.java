package icfpc2020.eval;

import icfpc2020.Draw;
import icfpc2020.eval.ast.ASTNode;
import icfpc2020.eval.ast.Generator;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author incubos
 */
public class EvaluatorTest {
    private static final String TEST_FUNCTION = "test";

    @NotNull
    private static LazyValue function(@NotNull final String code) throws Exception {
        final Evaluator evaluator;
        if (!code.contains("\n")) {
            evaluator =
                    new Evaluator(
                            new ByteArrayInputStream(
                                    (TEST_FUNCTION + " = " + code).getBytes(StandardCharsets.UTF_8)));
        } else {
            evaluator =
                    new Evaluator(
                            new ByteArrayInputStream(
                                    code.getBytes(StandardCharsets.UTF_8)));
        }
        return evaluator.getValue(TEST_FUNCTION);
    }

    private static void evalConst(
            @NotNull final String expectedResult,
            @NotNull final String code) throws Exception {
        assertEquals(
                expectedResult,
                function(code).asConst().toString());
    }

    private static void evalBinary(
            @NotNull final String expectedResult,
            @NotNull final String code) throws Exception {
        assertEquals(
                expectedResult,
                function(code).eval().asBinary().toString());
    }

    private static void evalImage(
            @NotNull final String expectedResult,
            @NotNull final String code) throws Exception {
        assertEquals(
                expectedResult,
                function(code).eval().asImage().toString());
    }


    private static void evalVar(
            @NotNull final String expectedResult,
            @NotNull final String code) throws Exception {
        assertEquals(expectedResult,
                function(code).eval().toString());
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
    public void cons() throws Exception {
        evalVar("ap ap x2 x0 x1",
                "x0 = 0\n" +
                        "x1 = 1\n" +
                        "x2 = 2\n" +
                        "test = ap ap ap cons x0 x1 x2");
        evalConst("0", "ap car ap ap cons 0 1");
        evalConst("1", "ap cdr ap ap cons 0 1");
    }

    @Test
    public void car() throws Exception {
        evalVar("ap x2 t",
                "x2 = 2\n" +
                        "test = ap car x2");
    }

    @Test
    public void cdr() throws Exception {
        evalVar("ap x2 f",
                "x2 = 2\n" +
                        "test = ap cdr x2");
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
    public void dem() throws Exception {
        evalVar("ap ap cons 1 ap ap cons 2 nil", "ap dem ap mod ap ap cons 1 ap ap cons 2 nil");
        evalVar("nil", "ap dem ap mod nil");
        evalVar("22", "ap dem ap mod 22");
    }


    @Test
    public void mod() throws Exception {
        evalBinary("1101100001110110001000", "ap mod ap ap cons 1 ap ap cons 2 nil");
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
                evaluator.getValue("test").asConst().toString());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void undefinedVariables() throws Exception {
        final String code =
                "x0 = 1\n" +
                        "test = ap ap add x0 x1";
        final Evaluator evaluator =
                new Evaluator(
                        new ByteArrayInputStream(
                                code.getBytes(StandardCharsets.UTF_8)));
        assertEquals(
                "3",
                evaluator.getValue("test").asConst().toString());
        fail("Have to throw UnsupportedOperationException");
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
                evaluator.getValue("test").asConst().toString());

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
    public void i() throws Exception {
        evalConst("1", "ap i 1");
        evalConst("8", "ap i ap ap mul 4 2");
    }

    @Test
    public void s() throws Exception {
        evalConst("3", "ap ap ap s add inc 1");
        evalConst("42", "ap ap ap s mul ap add 1 6");
    }

    @Test
    public void c() throws Exception {
        evalConst("3", "ap ap ap c add 1 2");
    }

    @Test
    public void b() throws Exception {
        evalConst("10", "ap ap ap b inc dec 10");
    }

    @Test(expected = ArithmeticException.class)
    public void pwr2() throws Exception {
        evalConst("1", "ap pwr2 0");
        evalConst("2", "ap pwr2 1");
        evalConst("256", "ap pwr2 8");
        evalConst("1267650600228229401496703205376", "ap pwr2 100");
        evalConst("2", "ap ap mul 2 ap ap ap ap eq 0 0 1 ap ap ap 1 ap mul 2 ap ap 1 pwr2 ap add -1 ap ap add -1 1");
        evalConst("1", "ap pwr2 ap dec 0");
        fail("Have to throw ArithmeticException");
    }

    @Test
    public void if0() throws Exception {
        evalConst("42", "ap ap ap if0 0 42 43");
        evalConst("42", "ap ap ap if0 1 43 42");
    }

    @Test
    public void nil() throws Exception {
        evalVar("t",
                "x0 = 1\n" +
                        "test = ap nil x0");
        evalConst("2", "ap ap ap nil 1 2 3");
        evalConst("3", "ap ap ap nil t 3 4");
        evalConst("4", "ap ap ap nil x0 4 5"); // x0 not defined and throw out
    }

    @Test
    public void isnil() throws Exception {
        evalVar("t",
                "x0 = nil\n" +
                        "test = ap isnil x0");
        evalVar("f",
                "x0 = ap ap cons 1 2\n" +
                        "test = ap isnil x0");
        evalVar("f",
                "x0 = ap ap cons 1 nil\n" +
                        "test = ap isnil x0");
        evalConst("1", "ap ap ap isnil nil 1 2");
        evalConst("2", "ap ap ap isnil 0 1 2");
    }

    @Test
    public void send() throws Exception {
        evalConst("1", "ap car ap send ap ap cons 0 nil");
    }

    @Test
    public void vec() throws Exception {
        evalVar("ap ap 2 0 1",
                "x0 = 0\n" +
                        "x1 = 1\n" +
                        "x2 = 2\n" +
                        "test = ap ap ap vec x0 x1 x2");
        evalConst("0", "ap car ap ap vec 0 1");
        evalConst("1", "ap cdr ap ap vec 0 1");
    }

    @Test
    public void draw()  throws Exception {
        evalImage("[]", "ap draw nil");
        evalImage("[(x=1, y=2)]", "ap draw ap ap vec 1 2");
        evalImage("[(x=1, y=2), (x=3, y=1)]", "ap draw ap ap cons ap ap vec 1 2 ap ap cons ap ap vec 3 1 nil");
    }

    @Test
    public void testGeneratedListParsed() throws IOException {
        final ASTNode listOfCoords = Generator.createListOfCoords(List.of(Draw.Coord.of(1, 2)));
        final Evaluator evaluator =
                new Evaluator(
                        new ByteArrayInputStream(
                                (TEST_FUNCTION + " = " + listOfCoords).getBytes(StandardCharsets.UTF_8)));
        assertEquals("ap ap cons ap ap cons 1 2 nil", evaluator.getValue(TEST_FUNCTION).toString());
    }
}
