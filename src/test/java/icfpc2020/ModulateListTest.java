package icfpc2020;

import icfpc2020.operators.Modulate;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ModulateListTest {

    public final CommandR nil = new CommandR(Command.Nil);
    public final CommandR ap = new CommandR(Command.App);
    public final CommandR cons = new CommandR(Command.Cons);
    public final CommandR lPar = new CommandR(Command.ListLPar);
    public final CommandR comma = new CommandR(Command.ListComma);
    public final CommandR rPar = new CommandR(Command.ListRPar);

    private NumberR number(long number) {
        return new NumberR(BigInteger.valueOf(number), new Board(0, 0));
    }


    @Test
    public void testModulateList_shouldProduceExpectedResult() {

//        "ap mod nil = [nil]\n" +

        Assert.assertEquals(ModulateList.mod(List.of(nil)).toString(),
                            "00");
        //                "ap mod ap ap cons nil nil = [0]\n" +
        Assert.assertEquals(ModulateList.mod(List.of(ap,
                                                     ap,
                                                     cons,
                                                     nil,
                                                     nil)).toString(),
                            "110000");
        //                "ap mod ap ap cons 0 nil = [0]\n" +
        Assert.assertEquals(ModulateList.mod(List.of(ap,
                                                     ap,
                                                     cons,
                                                     number(0),
                                                     nil)).toString(),
                            "1101000");
//                "ap mod ap ap cons 1 2 = [0]\n" +
        Assert.assertEquals(ModulateList.mod(List.of(ap,
                                                     ap,
                                                     cons,
                                                     number(1),
                                                     number(2))).toString(),
                            "110110000101100010");
        //                "ap mod ap ap cons 1 ap ap cons 2 nil = [0]\n" +
        Assert.assertEquals(ModulateList.mod(List.of(ap,
                                                     ap,
                                                     cons,
                                                     number(1),
                                                     ap,
                                                     ap,
                                                     cons,
                                                     number(2),
                                                     nil)).toString(),
                            "1101100001110110001000");
        //                "ap mod ( 1 , 2 ) = [0]\n" +
        Assert.assertEquals(ModulateList.mod(List.of(lPar,
                                                     number(1),
                                                     comma,
                                                     number(2),
                                                     rPar)).toString(),
                            "1101100001110110001000");
//                "ap mod ( 1 , ( 2 , 3 ) , 4 ) = [0]\n" +
        Assert.assertEquals(ModulateList.mod(List.of(lPar,
                                                     number(1),
                                                     comma,
                                                     lPar,
                                                     number(2),
                                                     comma,
                                                     number(3),
                                                     rPar,
                                                     comma,
                                                     number(4),
                                                     rPar)).toString(),
                            "1101100001111101100010110110001100110110010000");
    }
}
