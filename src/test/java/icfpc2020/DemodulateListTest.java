package icfpc2020;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class DemodulateListTest {

    private static String stringify(List<Pictogram> list) {
        return list.stream().map(Pictogram::toString).collect(Collectors.joining(" "));
    }

    @Test
    public void testModulateList_shouldProduceExpectedResult() {
        Assert.assertEquals("nil",
                stringify(DemodulateList.dem(new MessageImpl("00"))));
        Assert.assertEquals("ap ap cons nil nil",
                stringify(DemodulateList.dem(new MessageImpl("110000"))));
        Assert.assertEquals("ap ap cons 0 nil",
                stringify(DemodulateList.dem(new MessageImpl("1101000"))));
        Assert.assertEquals("ap ap cons 1 2",
                stringify(DemodulateList.dem(new MessageImpl("110110000101100010"))));
        Assert.assertEquals("ap ap cons 1 ap ap cons 2 nil",
                stringify(DemodulateList.dem(new MessageImpl("1101100001110110001000"))));
        Assert.assertEquals("ap ap cons 1 ap ap cons ap ap cons 2 ap ap cons 3 nil ap ap cons 4 nil",
                stringify(DemodulateList.dem(new MessageImpl("1101100001111101100010110110001100110110010000"))));
        Assert.assertEquals("ap ap cons 1 ap ap cons 0 nil",
                stringify(DemodulateList.dem(new MessageImpl("11011000011101000"))));
        Assert.assertEquals("ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons ap ap cons",
                stringify(DemodulateList.dem(new MessageImpl("11111111111111111111111111111111"))));
        Assert.assertEquals("ap ap cons 1 ap ap cons 81744 nil",
                stringify(DemodulateList.dem(new MessageImpl("110110000111011111100001001111110101000000"))));
        Assert.assertEquals("ap ap cons 1 ap ap cons 81740 nil",
                stringify(DemodulateList.dem(new MessageImpl("110110000111011111100001001111110100110000"))));
    }
}
