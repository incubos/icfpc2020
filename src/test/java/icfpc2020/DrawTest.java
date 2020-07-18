package icfpc2020;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class DrawTest {

    public final CommandR lPar = new CommandR(Command.ListLPar);
    public final CommandR ap = new CommandR(Command.App);
    public final CommandR vec = new CommandR(Command.Vector);
    public final CommandR comma = new CommandR(Command.ListComma);
    public final CommandR rPar = new CommandR(Command.ListRPar);

    private NumberR number(long number) {
        return new NumberR(BigInteger.valueOf(number), new Board(0, 0));
    }

    private static class DrawingBoard implements Consumer<Draw.Coord> {

        public final Set<Draw.Coord> set = new HashSet<>();

        @Override
        public void accept(final Draw.Coord coord) {
            set.add(coord);
        }
    }

    private void checkDraw(List<Pictogram> pictograms, Set<Draw.Coord> expectedResult) throws IOException {
        var board = new DrawingBoard();
        Draw.draw(pictograms, board);
        Assert.assertEquals(expectedResult, board.set);
        Files.createDirectories(Path.of("/tmp/images"));
        var image = new ImageRenderer("/tmp/images/"+Math.abs(pictograms.hashCode())+".png");
        board.set.forEach(image::putDot);
        image.persist();
    }

    @Test
    public void testDraw() throws IOException {

        // ap draw ( )   =   |picture1|
        checkDraw(List.of(lPar, rPar), Set.of());
        // ap draw ( ap ap vec 1 1 )   =   |picture2|
        checkDraw(List.of(lPar, ap, ap, vec, number(1), number(1), rPar),
                  Set.of(Draw.Coord.of(1, 1)));
        // ap draw ( ap ap vec 1 2 )   =   |picture3|
        checkDraw(List.of(lPar, ap, ap, vec, number(1), number(2), rPar),
                  Set.of(Draw.Coord.of(1, 2)));
        // ap draw ( ap ap vec 2 5 )   =   |picture4|
        checkDraw(List.of(lPar, ap, ap, vec, number(2), number(5), rPar),
                  Set.of(Draw.Coord.of(2, 5)));
        // ap draw ( ap ap vec 1 2 , ap ap vec 3 1 )   =   |picture5|
        checkDraw(List.of(lPar,
                          ap, ap, vec, number(1), number(2),
                          ap, ap, vec, number(3), number(1),
                          rPar),
                  Set.of(Draw.Coord.of(1, 2), Draw.Coord.of(3, 1)));
        // ap draw ( ap ap vec 5 3 , ap ap vec 6 3 , ap ap vec 4 4 , ap ap vec 6 4 , ap ap vec 4 5 )   =   |picture6|
        checkDraw(List.of(lPar,
                          ap, ap, vec, number(5), number(3),
                          ap, ap, vec, number(6), number(3),
                          ap, ap, vec, number(4), number(4),
                          ap, ap, vec, number(6), number(4),
                          ap, ap, vec, number(4), number(5),
                          rPar),
                  Set.of(Draw.Coord.of(5, 3),
                         Draw.Coord.of(6, 3),
                         Draw.Coord.of(4, 4),
                         Draw.Coord.of(6, 4),
                         Draw.Coord.of(4, 5)));
    }
}
