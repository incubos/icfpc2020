package icfpc2020.operators;

/**
 * https://message-from-space.readthedocs.io/en/latest/message14.html
 * :170 (dem)
 *
 * @author alesavin
 */
public class Demodulate {

    private final String number;

    public Demodulate(String number) {
        this.number = number;
    }

    public long dem() {
        boolean sign = false;
        if (number.startsWith("10")) sign = true;
        int i = 2;
        while(number.charAt(i) != '0') i++;
        int n = i - 2;
        if (n == 0) return 0L;
        String d = number.substring(i + 1, i + 1 + (n * 4));
        return sign ? -Long.valueOf(d, 2) : Long.valueOf(d, 2);
    }
}
