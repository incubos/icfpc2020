package icfpc2020;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static icfpc2020.BoardDecipher.dumpCommands;

public class BoardDecipherTest {

    @Test
    public void test4() throws IOException {
        final Board board = PngParser.loadPng("message4.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("=\n" +
                "0   =   0\n" +
                "1   =   1\n" +
                "2   =    2\n" +
                "3   =    3\n" +
                ". . . .\n" +
                "10  =    10\n" +
                "11  =    11\n" +
                ". . . .\n" +
                "-1  =   -1\n" +
                "-2  =    -2\n" +
                ". . . .", actual);
    }


    @Test
    public void test6() throws IOException {
        final Board board = PngParser.loadPng("message6.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("dec\n" +
                "ap dec 1 =   0\n" +
                "ap dec 2 =    1\n" +
                "ap dec 3 =    2\n" +
                "ap dec 4 =    3\n" +
                ". . . .\n" +
                "ap dec 1024 =  1023\n" +
                ". . . .\n" +
                "ap dec 0 =   -1\n" +
                "ap dec -1 =  -2\n" +
                "ap dec -2 =   -3\n" +
                ". . . .", actual);
    }



    @Test
    public void test8() throws IOException {
        final Board board = PngParser.loadPng("message8.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("x0 x1 x2 x3  x4   . . . .\n" +
                "ap ap add 0 x0 =  x0\n" +
                "ap ap add 0 x1 =  x1\n" +
                "ap ap add 0 x2 =   x2\n" +
                ". . . .\n" +
                "ap ap add x0 0 =  x0\n" +
                "ap ap add x1 0 =  x1\n" +
                "ap ap add x2 0 =   x2\n" +
                ". . . .\n" +
                "ap ap add x0 x1 =  ap ap add x1 x0\n" +
                ". . . .", actual);
    }

    @Test
    public void test13() throws IOException {
        final Board board = PngParser.loadPng("message13.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("mod\n" +
                "ap mod 0 =   [0]\n" +
                "ap mod 1 =   [1]\n" +
                "ap mod -1 =  [-1]\n" +
                "ap mod 2 =    [2]\n" +
                "ap mod -2 =   [-2]\n" +
                ". . . .\n" +
                "ap mod 16 =   [16]\n" +
                "ap mod -16 =  [-16]\n" +
                ". . . .\n" +
                "ap mod 255 =  [255]\n" +
                "ap mod -255 = [-255]\n" +
                "ap mod 256 =  [256]\n" +
                "ap mod -256 = [-256]\n" +
                ". . . .", actual);
    }

    @Test
    public void test15() throws IOException {
        final Board board = PngParser.loadPng("message15.png", 4, 4);
        final Board subBoard = board.subBoard(0, 100, 80, board.height - 100);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(subBoard);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("ap send0 x0 = x1\n" +
                "humans x0                         aliens\n" +
                "humans ~~~~~~    ap mod x0        aliens\n" +
                "humans                        x0  aliens\n" +
                "humans                        x1  aliens\n" +
                "humans       ap mod x1 ~~~~~~     aliens\n" +
                "humans x1                         aliens", actual);
    }

    @Test
    public void test30() throws IOException {
        final Board board = PngParser.loadPng("message30.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        // TODO add modulation
        Assert.assertEquals("( , )\n" +
                "( )    =   nil\n" +
                "( x0 )    =   ap ap cons x0 nil\n" +
                "( x0 , x1 )    =   ap ap cons x0 ap ap cons x1 nil\n" +
                "( x0 , x1 , x2  )   =    ap ap cons x0 ap ap cons x1 ap ap cons x2 nil\n" +
                "( x0 , x1 , x2  , x5 )    =   ap ap cons x0 ap ap cons x1 ap ap cons x2 ap ap cons x5 nil\n" +
                ". . . .", actual);
    }


    @Test
    public void test32() throws IOException {
        final Board board = PngParser.loadPng("message32.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("draw\n" +
                "ap draw ( )  =   |[]|\n" +
                "ap draw ( ap ap vec 1 1 ) =  |[1,1]|\n" +
                "ap draw ( ap ap vec 1 2 ) =   |[1,2]|\n" +
                "ap draw ( ap ap vec 2 5 ) =   |[2,5]|\n" +
                "ap draw ( ap ap vec 1 2 , ap ap vec 3 1 ) = |[1,2;3,1]|\n" +
                "ap draw ( ap ap vec 5 3 , ap ap vec 6 3 , ap ap vec 4 4 , ap ap vec 6 4 , ap ap vec 4 5 ) = |[4,4;4,5;5,3;6,3;6,4]|\n" +
                ". . . .", actual);
    }

    @Test
    public void test35() throws IOException {
        final Board board = PngParser.loadPng("message35.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.fail("Demodulation of lists doesn't work correctly here");
        Assert.assertEquals("mod cons\n" +
                "ap mod nil = [nil]\n" +
                "ap mod ap ap cons nil nil = [0]\n" +
                "ap mod ap ap cons 0 nil = [0]\n" +
                "ap mod ap ap cons 1 2 = [0]\n" +
                "ap mod ap ap cons 1 ap ap cons 2 nil = [0]\n" +
                "ap mod ( 1 , 2 ) = [0]\n" +
                "ap mod ( 1 , ( 2 , 3 ) , 4 ) = [0]\n" +
                ". . . .", actual);
    }

    @Test
    public void test36() throws IOException {
        final Board board = PngParser.loadPng("message36.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("deadline0\n" +
                "ap send0 ( 0 ) =  (  1 , deadline0 )", actual);
    }

    @Test
    public void test41() throws IOException {
        final Board board = PngParser.loadPng("message41.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("ap interact :7x7:0\n" +
                "ap ap :7x7:0 x0 x1 = ( 0 , ap ap cons x1 x0 , ( ap ap cons x1 x0 ) )\n" +
                ":7x7:0 =   ap ap b ap b ap ap s ap ap b ap b ap cons 0 ap ap c ap ap b b cons ap ap c cons nil ap ap c cons nil ap c cons\n" +
                "ap ap ap interact :7x7:0 nil ap ap vec 0 0 = ( ( ap ap vec 0 0 ) , ( |[0,0]| ) )\n" +
                "ap ap ap interact :7x7:0 ( ap ap vec 0 0 ) ap ap vec 2 3 = ( x2 , ( |[0,0;2,3]| ) )\n" +
                "ap ap ap interact :7x7:0 x2 ap ap vec 1 2 = ( x3 , ( |[0,0;1,2;2,3]| ) )\n" +
                "ap ap ap interact :7x7:0 x3 ap ap vec 3 2 = ( x4 , ( |[0,0;1,2;2,3;3,2]| ) )\n" +
                "ap ap ap interact :7x7:0 x4 ap ap vec 4 0 = ( x5 , ( |[0,0;1,2;2,3;3,2;4,0]| ) )\n" +
                ". . . .", actual);
    }

    @Test
    public void test42() throws IOException {
        final Board board = PngParser.loadPng("message42.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("ap interact galaxy = . . . .", actual);
    }

    @Test
    public void testTwitterGalaxy() throws  IOException {
        final Board board = PngParser.loadPng("twitter_galaxy.png", 1, 0);
//        System.out.println(board.toString());
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);

        final String actual = dumpCommands(decipher);
        Assert.assertEquals("ap ap mul pwr2 66 :5x5:1 :1x19:2\n" +
                "                                       :3x5:3 :3x4:4                                 humans\n" +
                "                                                           :7x7:5\n" +
                "                                                                                                    :7x5:6  :7x5:7\n" +
                "                                                                            galaxy\n" +
                "                                      :5x7:8                                                                                    :5x7:9\n" +
                "                                                                                                        :7x7:10\n" +
                "                                                     :7x5:11                                                                  aliens\n" +
                "                                                                            :7x7:12", actual);
    }




}
