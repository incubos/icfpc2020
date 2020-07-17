package icfpc2020;

import icfpc2020.operators.Demodulate;
import icfpc2020.operators.Modulate;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DemodulateTest {
    @Test
    public void test() {
        Assert.assertEquals(0L, new Demodulate("010").dem());
        List<Long> cases = Arrays.asList(1L, 256L, -1L, -256L, (long)Integer.MAX_VALUE, (long)Integer.MIN_VALUE);
        for (Long ca: cases) {
            String s = new Modulate(ca).mod();
            System.out.println(ca + " = [" + s + "]");
            Assert.assertEquals(ca.longValue(), new Demodulate(s).dem());
        }
    }
}
