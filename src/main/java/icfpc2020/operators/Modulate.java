package icfpc2020.operators;

import icfpc2020.Message;
import icfpc2020.MessageImpl;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * https://message-from-space.readthedocs.io/en/latest/message13.html
 * :341 (mod)
 *
 * @author alesavin
 */
public class Modulate {

    private Modulate() {
    }

    public static Message mod(BigInteger number) {
        StringBuilder sb = new StringBuilder();
        if (number.compareTo(BigInteger.ZERO) < 0)
            sb.append("10");
        else
            sb.append("01");
        if (!number.equals(BigInteger.ZERO)) {
            String bits = number.abs().toString(2);
            int trailingZeros = 4 - bits.length() % 4;
            sb.append("1".repeat((trailingZeros + bits.length()) / 4))
              .append("0")
              .append("0".repeat(trailingZeros))
              .append(bits);
        } else {
            sb.append("0");
        }
        return new MessageImpl(sb.toString());
    }
}
