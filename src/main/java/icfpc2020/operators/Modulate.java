package icfpc2020.operators;

/**
 * https://message-from-space.readthedocs.io/en/latest/message13.html
 * :341 (mod)
 *
 * @author alesavin
 */
public class Modulate {

    private final long number;

    public Modulate(long number) {
        this.number = number;
    }

    public String mod() {
        StringBuilder sb = new StringBuilder();
        if (number < 0)
            sb.append("10");
        else
            sb.append("01");
        if (number != 0L) {
            String bits = Long.toBinaryString(Math.abs(number));
            int trailingZeros = 4 - bits.length() % 4;
            sb.append("1".repeat((trailingZeros + bits.length()) / 4))
                    .append("0")
                    .append("0".repeat(trailingZeros))
                    .append(bits);
        } else {
            sb.append("0");
        }
        return sb.toString();
    }
}
