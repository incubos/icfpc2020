package icfpc2020;

import java.math.BigInteger;

public class Tokens {
    interface Token {
    }

    public static class Number implements Token {
        public final BigInteger number;

        private Number(final BigInteger number) {
            this.number = number;
        }

        public static Number of(final long number) {
            return new Number(BigInteger.valueOf(number));
        }

        public static Number of(final String number) {
            return new Number(new BigInteger(number));
        }

        public static Number of(final BigInteger number) {
            return new Number(number);
        }

        @Override
        public String toString() {
            return number.toString();
        }
    }
}
