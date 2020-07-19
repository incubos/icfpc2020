package icfpc2020.eval.value;

import icfpc2020.MessageImpl;
import icfpc2020.eval.Evaluator;
import icfpc2020.operators.Demodulate;
import icfpc2020.operators.Modulate;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DemodulateValue implements LazyValue {
    public static final LazyValue INSTANCE = new DemodulateValue();
    private static final Logger log = LoggerFactory.getLogger(DemodulateValue.class);
    private static String apply = "ap";
    private static String cons = "cons";
    private static String nil = "nil";

    private DemodulateValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        String s = arg.asBinary().toString();
        StringBuilder result = new StringBuilder();
        while (!s.isEmpty()) {
            if (s.startsWith("11")) {
                result.append(apply)
                        .append(" ")
                        .append(apply)
                        .append(" ")
                        .append(cons)
                        .append(" ");
                s = s.substring(2);
            } else if (s.startsWith("00")) {
                result.append(nil)
                        .append(" ");
                s = s.substring(2);
            } else {
                var bi = Demodulate.dem(new MessageImpl(s));
                result.append(bi.toString())
                        .append(" ");
                var modulateLength = Modulate.mod(bi).toString().length();
                s = s.substring(modulateLength);
            }
        }

        try {
            Evaluator evaluator = new Evaluator(
                    new ByteArrayInputStream(
                            ("demodulated = " + result).getBytes(StandardCharsets.UTF_8)));
            return evaluator.getValue("demodulated");
        } catch (IOException e) {
            log.error("Error caught while parsing demodulate result, result=<{}>, modulated=<{}>"
                    , result.toString(), s, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "dem";
    }
}
