package icfpc2020;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class BoardDecipherTest {


    @Test
    public void test4() throws IOException {
        // Load command pictograms!
        Command.values();
        final Board board = PngParser.loadPng("message4.png", 4, 4);
//        System.out.println(board.toString());
        final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
        final String actual = dumpCommands(decipher);
        Assert.assertEquals(
                "=\n" +
                        "?=?\n" +
                        "?=?\n" +
                        "2=2\n" +
                        "3=3\n" +
                        "....\n" +
                        "10=10\n" +
                        "11=11\n" +
                        "....\n" +
                        "1=1\n" +
                        "2=2\n" +
                        "....\n",
                actual);
    }

    private static String dumpCommands(List<List<ParseResult>> decipher) {
        final StringBuilder b = new StringBuilder();
        decipher.forEach(row -> {
            row.forEach(r -> {
                b.append(r.toString());
            });
            b.append("\n");
        });
        return b.toString();
    }
}
