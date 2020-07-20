package icfpc2020;

import icfpc2020.eval.value.DemodulateValue;
import icfpc2020.operators.Demodulate;
import icfpc2020.operators.Modulate;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemodulateTest {
    @Test
    public void test() {
        Assert.assertEquals(BigInteger.ZERO, Demodulate.dem(new MessageImpl("010")));
        Assert.assertEquals(BigInteger.valueOf(128), Demodulate.dem(new MessageImpl("011101000000000110000110000")));
        List<BigInteger> cases = Stream.of(1L, 128L, 256L, -1L, -256L, Long.MAX_VALUE,
                Long.MIN_VALUE).map(BigInteger::valueOf)
                .collect(Collectors.toList());
        for (BigInteger ca : cases) {
            String s = Modulate.mod(ca).toString();
            System.out.println(ca + " = [" + s + "]");
            Assert.assertEquals(ca, Demodulate.dem(new MessageImpl(s)));
        }
    }

    @Test
    public void test2() {
        DemodulateValue.demodulate(
                "1101100001110101111011110000100000000110110000111110111100001110000001101100001110111001000000001111011100001000011011101000000000110000110000");
    }
}
