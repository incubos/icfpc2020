package icfpc2020;

/**
 * https://message-from-space.readthedocs.io/en/latest/message13.html
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
        String bits = "";
        if (number > 0L)
            bits = Long.toBinaryString(number);
        else if (number < 0L)
            bits = Long.toBinaryString(-number);
        bits = "0".repeat(bits.length() % 4) + bits;
        sb.append("1".repeat(Math.max(0, bits.length() / 4))).append("0");
        sb.append(bits);
        return sb.toString();
    }
}
