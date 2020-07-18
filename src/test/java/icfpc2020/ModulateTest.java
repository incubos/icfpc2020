package icfpc2020;

import icfpc2020.operators.Modulate;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class ModulateTest {
    @Test
    public void test() {
        Assert.assertEquals("010", new Modulate(BigInteger.valueOf(0L)).mod());
        Assert.assertEquals("01100001", new Modulate(BigInteger.valueOf(1L)).mod());
        Assert.assertEquals("011110000100000000", new Modulate(BigInteger.valueOf(256L)).mod());
        Assert.assertEquals("10100001", new Modulate(BigInteger.valueOf(-1L)).mod());
        Assert.assertEquals("101110000100000000", new Modulate(BigInteger.valueOf(-256L)).mod());
    }
}
