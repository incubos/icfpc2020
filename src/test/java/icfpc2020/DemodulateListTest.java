package icfpc2020;

import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void testMList() {
        Assert.assertEquals( "[1,2,nil,nil]",
            DemodulateList.demMList(new MessageImpl("110110000111011000101100110000")).toString());
        Assert.assertEquals( "[nil]",
                DemodulateList.demMList(new MessageImpl("110000")).toString());
        Assert.assertEquals( "[0]",
                DemodulateList.demMList(new MessageImpl("1101000")).toString());
        Assert.assertEquals( "[1,[2,3],4]",
                DemodulateList.demMList(new MessageImpl("1101100001111101100010110110001100110110010000")).toString());
        Assert.assertEquals( "[1,0,[256,0,[512,1,64],[16,128],[1,2,1,2]],nil]",
                DemodulateList.demMList(new MessageImpl("110110000111010111101111000010000000011010111101111000100000000011011000011101110010000000011110111000010000110111010000000001111011000011101100010110110000111011000100000110000")).toString());
        Assert.assertEquals( "[1,2,[256,0,[512,1,64],[16,128],[1,2,1,2]],nil]",
                DemodulateList.demMList(new MessageImpl("11011000011101100010111101111000010000000011010111101111000100000000011011000011101110010000000011110111000010000110111010000000001111011000011101100010110110000111011000100000110000")).toString());
        Assert.assertEquals( "[1,2,[256,1,[448,1,64],[16,128],nil],nil]",
                DemodulateList.demMList(new MessageImpl("110110000111011000101111011110000100000000110110000111110111100001110000001101100001110111001000000001111011100001000011011101000000000110000110000")).toString());
    }
}
