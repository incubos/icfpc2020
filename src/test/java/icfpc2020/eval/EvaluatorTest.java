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
                evaluator.evaluate("test").apply().toString());
    }

    @Test
    public void const42() throws Exception {
        eval("42", "test = 42");
    }

    @Test
    public void inc0() throws Exception {
        eval("1", "test = ap inc 0");
    }

    @Test
    public void inc1() throws Exception {
        eval("2", "test = ap inc 1");
    }

    @Test
    public void incincinc() throws Exception {
        eval("3", "test = ap inc ap inc ap inc 0");
    }

    @Test
    public void inc_1() throws Exception {
        eval("0", "test = ap inc -1");
    }

    @Test
    public void inc_2() throws Exception {
        eval("-1", "test = ap inc -2");
    }

    @Test
    public void dec0() throws Exception {
        eval("-1", "test = ap dec 0");
    }

    @Test
    public void dec1() throws Exception {
        eval("0", "test = ap dec 1");
    }

    @Test
    public void dec2() throws Exception {
        eval("1", "test = ap dec 2");
    }

    @Test
    public void dec_1() throws Exception {
        eval("-2", "test = ap dec -1");
    }
}
