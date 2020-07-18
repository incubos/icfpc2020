package icfpc2020;

import icfpc2020.operators.Modulate;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class ModulateTest {
    @Test
    public void test() {
        Assert.assertEquals("010", Modulate.mod(BigInteger.valueOf(0L)).toString());
        Assert.assertEquals("01100001", Modulate.mod(BigInteger.valueOf(1L)).toString());
        Assert.assertEquals("011110000100000000", Modulate.mod(BigInteger.valueOf(256L)).toString());
        Assert.assertEquals("10100001", Modulate.mod(BigInteger.valueOf(-1L)).toString());
        Assert.assertEquals("101110000100000000", Modulate.mod(BigInteger.valueOf(-256L)).toString());
    }
}
