package icfpc2020;

import icfpc2020.eval.Evaluator;
import icfpc2020.eval.ast.Generator;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.galaxy.Eval;
import icfpc2020.galaxy.Expr;
import icfpc2020.galaxy.Vect;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class DrawTest {

    public static final Logger log = LoggerFactory.getLogger(DummyTest.class);
    public final CommandR lPar = new CommandR(Command.ListLPar);
    public final CommandR ap = new CommandR(Command.App);
    public final CommandR vec = new CommandR(Command.Vector);
    public final CommandR comma = new CommandR(Command.ListComma);
    public final CommandR rPar = new CommandR(Command.ListRPar);
    private static Path tempDirectory;

    @BeforeClass
    public static void oneTimeSetUp() {
        try {
            tempDirectory = Files.createTempDirectory("images");
            log.info("Writing temp images to {}", tempDirectory.toString());
            tempDirectory.toFile().deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private NumberR number(long number) {
        return new NumberR(BigInteger.valueOf(number), new Board(0, 0));
    }

    private Draw.Coord coord(long x, long y) {
        return Draw.Coord.of(BigInteger.valueOf(x), BigInteger.valueOf(y));
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
        var image = new ImageRenderer(tempDirectory.toString() + Math.abs(pictograms.hashCode()) + ".png");
        board.set.forEach(image::putDot);
        image.persist();
    }

    private void checkDraw(Expr images, Set<Draw.Coord> expectedResult) throws IOException {
        var board = new DrawingBoard();
        Draw.draw(images, board);
        Assert.assertEquals(expectedResult, board.set);
        var image = new ImageRenderer(tempDirectory.toString() + Math.abs(images.hashCode()) + ".png");
        board.set.forEach(image::putDot);
        image.persist();
    }

    private void checkDraw(LazyValue value, Set<Draw.Coord> expectedResult) throws IOException {
        var board = new DrawingBoard();
        Draw.draw(value, board);
        Assert.assertEquals(expectedResult, board.set);
        var image = new ImageRenderer(tempDirectory.toString() + Math.abs(value.hashCode()) + ".png");
        board.set.forEach(image::putDot);
        image.persist();
    }



    @Test
    public void testDraw() throws IOException {

        // ap draw ( )   =   |picture1|
        checkDraw(List.of(lPar, rPar), Set.of());
        // ap draw ( ap ap vec 1 1 )   =   |picture2|
        checkDraw(List.of(lPar, ap, ap, vec, number(1), number(1), rPar),
                  Set.of(coord(1, 1)));
        // ap draw ( ap ap vec 1 2 )   =   |picture3|
        checkDraw(List.of(lPar, ap, ap, vec, number(1), number(2), rPar),
                  Set.of(coord(1, 2)));
        // ap draw ( ap ap vec 2 5 )   =   |picture4|
        checkDraw(List.of(lPar, ap, ap, vec, number(2), number(5), rPar),
                  Set.of(coord(2, 5)));
        // ap draw ( ap ap vec 1 2 , ap ap vec 3 1 )   =   |picture5|
        checkDraw(List.of(lPar,
                          ap, ap, vec, number(1), number(2),
                          ap, ap, vec, number(3), number(1),
                          rPar),
                  Set.of(coord(1, 2), coord(3, 1)));
        // ap draw ( ap ap vec 5 3 , ap ap vec 6 3 , ap ap vec 4 4 , ap ap vec 6 4 , ap ap vec 4 5 )   =   |picture6|
        checkDraw(List.of(lPar,
                          ap, ap, vec, number(5), number(3),
                          ap, ap, vec, number(6), number(3),
                          ap, ap, vec, number(4), number(4),
                          ap, ap, vec, number(6), number(4),
                          ap, ap, vec, number(4), number(5),
                          rPar),
                  Set.of(coord(5, 3),
                         coord(6, 3),
                         coord(4, 4),
                         coord(6, 4),
                         coord(4, 5)));
    }

    @Test
    public void testDrawWithCommands() throws IOException {
        final Eval eval = new Eval();

        // ap draw ( )   =   |picture1|
        checkDraw(eval.generateCoordList(List.of()), Set.of());
        // ap draw ( ap ap vec 1 1 )   =   |picture2|
        checkDraw(eval.generateCoordList(List.of(new Vect(1, 1))),
                Set.of(coord(1, 1)));
        // ap draw ( ap ap vec 1 2 )   =   |picture3|
        checkDraw(eval.generateCoordList(List.of(new Vect(1, 2))),
                Set.of(coord(1, 2)));
        // ap draw ( ap ap vec 2 5 )   =   |picture4|
        checkDraw(eval.generateCoordList(List.of(new Vect(2, 5))),
                Set.of(coord(2, 5)));
        // ap draw ( ap ap vec 1 2 , ap ap vec 3 1 )   =   |picture5|
        checkDraw(eval.generateCoordList(List.of(new Vect(1, 2), new Vect(3, 1))),
                Set.of(coord(1, 2), coord(3, 1)));
        // ap draw ( ap ap vec 5 3 , ap ap vec 6 3 , ap ap vec 4 4 , ap ap vec 6 4 , ap ap vec 4 5 )   =   |picture6|
        checkDraw(eval.generateCoordList(List.of(
                new Vect(5, 3),
                new Vect(6, 3),
                new Vect(4, 4),
                new Vect(6, 4),
                new Vect(4, 5))),
                Set.of(coord(5, 3),
                        coord(6, 3),
                        coord(4, 4),
                        coord(6, 4),
                        coord(4, 5)));
    }

    @Test
    public void testDrawWithASTCommands() throws IOException {
        // ap draw ( ap ap vec 1 2 )   =   |picture3|
        checkDraw(toLazyValue(List.of(Draw.Coord.of(1, 2))), Set.of(coord(1, 2)));

    }

    @NotNull
    private LazyValue toLazyValue(List<Draw.Coord> coordList) throws IOException {
        final Evaluator evaluator =
                new Evaluator(new ByteArrayInputStream(
                        ("test = " + Generator.createListOfCoords(coordList)).getBytes(StandardCharsets.UTF_8)));
        return evaluator.getValue("test");
    }

}
