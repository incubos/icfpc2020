package icfpc2020.eval.value;

import icfpc2020.Draw;
import icfpc2020.MessageImpl;
import icfpc2020.eval.Evaluator;
import icfpc2020.operators.Demodulate;
import icfpc2020.operators.Modulate;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DemodulateValue implements LazyValue {
    public static final LazyValue INSTANCE = new DemodulateValue();
    private static final Logger log = LoggerFactory.getLogger(DemodulateValue.class);
    private static String apply = "ap";
    private static String cons = "cons";
    private static String nil = "nil";

    private DemodulateValue() {
    }

    enum Token {
        cons,
        nil,
        plus,
        minus;

        public static Token fromChars(char first, char second) {
            if (first == '1' && second == '1') {
                return cons;
            } else if (first == '1' && second == '0') {
                return minus;
            } else if (first == '0' && second == '1') {
                return plus;
            } else if (first == '0' && second == '0') {
                return nil;
            } else {
                throw new UnsupportedOperationException("expect a pair of [0,1], got " + first + second);
            }
        }
    }

    private static int readNumber(final String s, final int pos, final StringBuilder sb,
                                  final boolean sign) {
        int currentPosition = pos;
        while (s.charAt(currentPosition) != '0') currentPosition++;
        int n = currentPosition - pos;
        if (n == 0) {
            sb.append("0 ");
            return pos + 1;
        }
        int endIndex = currentPosition + 1 + (n * 4);
        String d = s.substring(currentPosition + 1, endIndex);
        sb.append((sign ? new BigInteger(d, 2) : new BigInteger(d, 2).negate()).toString())
          .append(" ");
        return endIndex;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        String s = arg.asBinary().toString();
        final String dem = demodulate(s);
        try {
            Evaluator evaluator = new Evaluator(
                    new ByteArrayInputStream(
                            ("demodulated = " + dem).getBytes(StandardCharsets.UTF_8)));
            return evaluator.getValue("demodulated");
        } catch (IOException e) {
            log.error("Error caught while parsing demodulate result, result=<{}>, modulated=<{}>"
                    , dem, s, e);
            throw new RuntimeException(e);
        }
    }

    public static List<Object> eval(String message) throws IOException {
        Evaluator evaluator = new Evaluator("test=" + DemodulateValue.demodulate(message));
        return (List<Object>) eval(evaluator.getValue("test").force());
    }

    private static Object eval(LazyValue lazyValue) {
        var acc = new ArrayList<>();
        LazyValue current = lazyValue;
        while (true) {
            if (current instanceof ConsValue) {
                LazyValue left = ((ConsValue) current).left.force();
                LazyValue right = ((ConsValue) current).right.force();
                if (left instanceof ConstantValue && right instanceof ConstantValue) {
                    log.trace("({},{})", left.asConst(), right.asConst());
                    return Draw.Coord.of(left.asConst(), right.asConst());
                } else if (left instanceof NilValue) {
                    acc.add(null);
                    log.trace("null");
                    current = right;
                } else if (left instanceof ConsValue) {
                    log.trace("(");
                    acc.add(eval(left));
                    current = right;
                } else if (left instanceof ConstantValue) {
                    acc.add(left.asConst());
                    log.trace("{}", left.asConst());
                    current = right;
                }
            } else if (current instanceof NilValue) {
                log.trace(")");
                return acc;
            } else if (current instanceof ConstantValue) {
                acc.add(current.asConst());
                log.trace("{}", current.asConst());
                return acc;
            }
        }
    }

    @NotNull
    public static String demodulate(String s) {
        StringBuilder result = new StringBuilder();
        int pos = 0;
        while (pos < s.length()) {
            var first = s.charAt(pos++);
            var second = s.charAt(pos++);
            switch (Token.fromChars(first, second)) {
                case cons:
                    result.append(apply)
                          .append(" ")
                          .append(apply)
                          .append(" ")
                          .append(cons)
                          .append(" ");
                    break;
                case plus: {
                    pos = readNumber(s, pos, result, true);
                    break;
                }
                case minus: {
                    pos = readNumber(s, pos, result, false);
                    break;
                }
                case nil:
                    result.append(nil)
                          .append(" ");
            }
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return "dem";
    }
}
