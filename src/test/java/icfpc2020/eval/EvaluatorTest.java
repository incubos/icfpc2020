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
            @NotNull final String code,
            @NotNull final String expectedResult) throws Exception {
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
        eval("test = 42", "42");
    }

    @Test
    public void inc0() throws Exception {
        eval("test = ap inc 0", "1");
    }

    @Test
    public void inc1() throws Exception {
        eval("test = ap inc 1", "2");
    }

    @Test
    public void incincinc() throws Exception {
        eval("test = ap inc ap inc ap inc 0", "3");
    }

    @Test
    public void inc_1() throws Exception {
        eval("test = ap inc -1", "0");
    }

    @Test
    public void inc_2() throws Exception {
        eval("test = ap inc -2", "-1");
    }

}
