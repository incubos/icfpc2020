package icfpc2020;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class Draw {
    private static final Logger log = LoggerFactory.getLogger(Draw.class);

    private Draw() {
    }

    private static final Set<Command> ignoreCommands = EnumSet.of(Command.App, Command.Cons,
                                                                  Command.Nil, Command.ListRPar,
                                                                  Command.ListLPar,
                                                                  Command.ListComma);

    private static final Command startToken = Command.Vector;

    public static class Coord {
        public final long x;
        public final long y;

        public Coord(final long x, final long y) {
            this.x = x;
            this.y = y;
        }

        public static Coord of(final long x, final long y) {
            return new Coord(x, y);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Coord coord = (Coord) o;
            return x == coord.x &&
                    y == coord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(x=" + x + ", y=" + y + ')';
        }
    }

    private static BigInteger getNumber(Pictogram pictogram) {
        if (pictogram instanceof NumberR) {
            return ((NumberR) pictogram).n;
        } else {
            throw new UnsupportedOperationException("Draw protocol expects two number after 'vec' command");
        }
    }

    public static void draw(List<Pictogram> commands, Consumer<Coord> drawingBoard) {
        var iter = commands.iterator();
        var index = 0;
        while (iter.hasNext()) {
            var pictogram = iter.next();
            index++;
            if (pictogram instanceof CommandR) {
                var command = ((CommandR) pictogram).command;
                if (ignoreCommands.contains(command)) {
                    continue;
                } else if (startToken == command) {
                    BigInteger x, y;
                    if (iter.hasNext()) {
                        x = getNumber(iter.next());
                        index++;
                    } else {
                        log.error("Not enough commands, expected two numbers, commands={}, " +
                                          "position={}", commands, index);
                        throw new UnsupportedOperationException("Draw protocol expects at least " +
                                                                        "two pictograms after 'vec' command");
                    }
                    if (iter.hasNext()) {
                        y = getNumber(iter.next());
                        index++;
                    } else {
                        log.error("Not enough commands, expected two numbers, commands={}, " +
                                          "position={}", commands, index);
                        throw new UnsupportedOperationException("Draw protocol expects at least " +
                                                                        "two pictograms after 'vec' command");
                    }
                    drawingBoard.accept(new Coord(x.longValue(), y.longValue()));
                } else {
                    log.error("Found unexpected command when drawing, command={}, position={}commands={}",
                              command,
                              index,
                              commands);
                    throw new UnsupportedOperationException("unexpected command");
                }
            } else {
                log.error("Found unexpected pictogram when drawing, pictogram={}, position={}, commands={}",
                          pictogram,
                          index,
                          commands);
                throw new UnsupportedOperationException("unexpected pictogram");
            }
        }
    }
}
