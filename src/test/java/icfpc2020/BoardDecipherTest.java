package icfpc2020;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDecipherTest {


    @Test
    public void test4() throws IOException {
        final Board board = PngParser.loadPng("message4.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("=\n" +
                        "0 = 0\n" +
                        "1 = 1\n" +
                        "2 = 2\n" +
                        "3 = 3\n" +
                        ". . . .\n" +
                        "10 = 10\n" +
                        "11 = 11\n" +
                        ". . . .\n" +
                        "1 = 1\n" +
                        "2 = 2\n" +
                        ". . . .",
                actual);
    }


    @Test
    public void test5() throws IOException {
        final Board board = PngParser.loadPng("message5.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("inc\n" +
                        "app inc 0 = 1\n" +
                        "app inc 1 = 2\n" +
                        "app inc 2 = 3\n" +
                        "app inc 3 = 4\n" +
                        ". . . .\n" +
                        "app inc 300 = 301\n" +
                        "app inc 301 = 302\n" +
                        ". . . .\n" +
                        "app inc 1 = 0\n" +
                        "app inc 2 = 1\n" +
                        "app inc 3 = 2\n" +
                        ". . . .",
                actual);
    }

    @Test
    public void test6() throws IOException {
        final Board board = PngParser.loadPng("message6.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("dec\n" +
                "app dec 1 = 0\n" +
                "app dec 2 = 1\n" +
                "app dec 3 = 2\n" +
                "app dec 4 = 3\n" +
                ". . . .\n" +
                "app dec 1024 = 1023\n" +
                ". . . .\n" +
                "app dec 0 = 1\n" +
                "app dec 1 = 2\n" +
                "app dec 2 = 3\n" +
                ". . . .", actual);
    }

    @Test
    public void test7() throws IOException {
        final Board board = PngParser.loadPng("message7.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("add\n" +
                "app app add 1 2 = 3\n" +
                "app app add 2 1 = 3\n" +
                "app app add 0 1 = 1\n" +
                "app app add 2 3 = 5\n" +
                "app app add 3 5 = 8\n" +
                ". . . .", actual);
    }

    @Test
    public void test8() throws IOException {
        final Board board = PngParser.loadPng("message8.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("x0 x1 x2 x3 x4 0 0 0 0\n" +
                "app app add 0 x0 = x0\n" +
                "app app add 0 x1 = x1\n" +
                "app app add 0 x2 = x2\n" +
                ". . . .\n" +
                "app app add x0 0 = x0\n" +
                "app app add x1 0 = x1\n" +
                "app app add x2 0 = x2\n" +
                ". . . .\n" +
                "app app add x0 x1 = app app add x1 x0\n" +
                ". . . .", actual);
    }

//    @Test
//    public void test13() throws IOException {
//        final Board board = PngParser.loadPng("message13.png", 4, 4);
//        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
//        final String actual = dumpCommands(decipher);
//        // TODO add modulation
//        Assert.assertEquals("mod\n" +
//                "app mod 0 = ?\n" +
//                "app mod 1 = ?\n" +
//                "app mod 1 = ?\n" +
//                "app mod 2 = ?\n" +
//                "app mod 2 = ?\n" +
//                ". . . .\n" +
//                "app mod 16 = ?\n" +
//                "app mod 16 = ?\n" +
//                ". . . .\n" +
//                "app mod 255 = ?\n" +
//                "app mod 255 = ?\n" +
//                "app mod 256 = ?\n" +
//                "app mod 256 = ?\n" +
//                ". . . .", actual);
//    }

    @Test
    public void test30() throws IOException {
        final Board board = PngParser.loadPng("message30.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        // TODO add modulation
        Assert.assertEquals("( , )\n" +
                "( ) = nil\n" +
                "( x0 ) = app app cons x0 nil\n" +
                "( x0 , x1 ) = app app cons x0 app app cons x1 nil\n" +
                "( x0 , x1 , x2 ) = app app cons x0 app app cons x1 app app cons x2 nil\n" +
                "( x0 , x1 , x2 , x5 ) = app app cons x0 app app cons x1 app app cons x2 app app cons x5 nil\n" +
                ". . . .", actual);
    }


    @Test
    public void test32() throws IOException {
        final Board board = PngParser.loadPng("message32.png", 4, 4);
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals("draw\n" +
                "app draw ( ) = |picture|\n" +
                "app draw ( app app vec 1 1 ) = |picture|\n" +
                "app draw ( app app vec 1 2 ) = |picture|\n" +
                "app draw ( app app vec 2 5 ) = |picture|\n" +
                "app draw ( app app vec 1 2 , app app vec 3 1 ) = |picture|\n" +
                "app draw ( app app vec 5 3 , app app vec 6 3 , app app vec 4 4 , app app vec 6 4 , app app vec 4 5 ) = |picture|\n" +
                ". . . .", actual);
    }


    private static String dumpCommands(List<List<ParseResult>> decipher) {
        return decipher.stream().map( row ->
            row.stream().map(ParseResult::toString).collect(Collectors.joining(" "))
        ).collect(Collectors.joining("\n"));
    }
}
