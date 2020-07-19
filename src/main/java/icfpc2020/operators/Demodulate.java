package icfpc2020.operators;

import icfpc2020.Message;

import java.math.BigInteger;

/**
 * https://message-from-space.readthedocs.io/en/latest/message14.html
 * :170 (dem)
 *
 * @author alesavin
 */
public class Demodulate {

    private Demodulate() {
    }

    public static BigInteger dem(Message message) {

        var number = message.toString();
        boolean sign = false;
        if (number.startsWith("10")) sign = true;
        int i = 2;
        while (number.charAt(i) != '0') i++;
        int n = i - 2;
        if (n == 0) return BigInteger.ZERO;
        String d = number.substring(i + 1, i + 1 + (n * 4));
        var bi = new BigInteger(d, 2);
        return sign ? bi.negate() : bi;
    }

    public static class ParseResult {
        public final BigInteger number;
        public final int lengthRead;

        public ParseResult(final BigInteger number, final int lengthRead) {
            this.number = number;
            this.lengthRead = lengthRead;
        }
    }

    public static ParseResult dem(String message, int pos) {
        boolean sign = false;
        if (message.startsWith("10")) sign = true;
        int i = pos + 2;
        while (message.charAt(i) != '0') i++;
        int n = i - 2;
        if (n == 0) return new ParseResult(BigInteger.ZERO, 3);
        int endPos = i + 1 + (n * 4);
        String d = message.substring(i + 1, endPos);
        var bi = new BigInteger(d, 2);
        return new ParseResult(sign ? bi.negate() : bi, endPos - pos);
    }
}
