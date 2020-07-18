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
    public void inc() throws Exception {
        eval("test = ap inc 0", "1");
    }

    @Test
    public void inc3() throws Exception {
        eval("test = ap inc ap inc ap inc 0", "3");
    }
}
