package icfpc2020;

import icfpc2020.operators.Demodulate;
import icfpc2020.operators.Modulate;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DemodulateTest {
    @Test
    public void test() {
        Assert.assertEquals(0L, new Demodulate("010").dem());
        List<BigInteger> cases = Arrays.asList(1L, 256L, -1L, -256L, (long)Integer.MAX_VALUE,
                                               (long)Integer.MIN_VALUE).stream().map(BigInteger::valueOf)
                .collect(Collectors.toList());
        for (BigInteger ca: cases) {
            String s = new Modulate(ca).mod();
            System.out.println(ca + " = [" + s + "]");
            Assert.assertEquals(ca.longValue(), new Demodulate(s).dem());
        }
    }
}
