package icfpc2020;

import icfpc2020.operators.Demodulate;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

class Pictogram {
    final Board board;

    public Pictogram(Board board) {
        this.board = board;
    }

    private static final Map<String, String> KNOWN_FIGURES = new HashMap(Map.of(
            ":7x7-141854172500000", "humans",
            ":7x7-389184811999064", "aliens",
            ":21x5-1229782323916997700", "~~~~~~"
    ));

    @Override
    public String toString() {
        long sum = 0;
        long power = 1;
        for (int x = 0; x < board.width; x++) {
            for (int y = 0; y < board.height; y++) {
                if (board.getValue(x, y) == 1) {
                    sum += power;
                }
                power *= 2;
            }
        }

        final String s = ":" + board.width + "x" + board.height + "-" + sum;
        if (KNOWN_FIGURES.containsKey(s)) {
            return KNOWN_FIGURES.get(s);
        }
        final String encoding = ":" + board.width + "x" + board.height + ":" + (KNOWN_FIGURES.size() - 3);
        KNOWN_FIGURES.put(s, encoding);
        return encoding;
    }
}

class NumberR extends Pictogram {
    final BigInteger n;

    public NumberR(BigInteger n, Board board) {
        super(board);
        this.n = n;
    }

    @Override
    public String toString() {
        return String.valueOf(n);
    }
}

class VariableR extends Pictogram {
    final BigInteger n;

    public VariableR(BigInteger n, Board board) {
        super(board);
        this.n = n;
    }

    @Override
    public String toString() {
        return "x" + n;
    }
}

class CommandR extends Pictogram {
    final Command command;

    public CommandR(Command command) {
        super(command.getBoard());
        this.command = command;
    }

    @Override
    public String toString() {
        return command.toString();
    }
}

class DotR extends Pictogram {

    public DotR(Board board) {
        super(board);
    }

    @Override
    public String toString() {
        return ".";
    }
}

class PictureR extends Pictogram {

    public PictureR(Board board) {
        super(board);
    }

    @Override
    public String toString() {
        final List<String> points = new ArrayList<>();
        for (int x = 0; x < board.width; x++) {
            for (int y = 0; y < board.height; y++) {
                if (board.getValue(x, y) == 1) {
                    points.add(x + "," + y);
                }
            }
        }
        // Show values
        return "|[" + String.join(";", points) + "]|";
    }
}

class ModulationR extends Pictogram {

    public ModulationR(Board board) {
        super(board);
    }

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder();
        for (int x = 0; x < board.width; x++) {
            b.append(board.getValue(x, 0));
        }
        try {
            return "[" + new Demodulate(b.toString()).dem() + "]";
        } catch (Exception e) {
            // Demodulation error?
            return "[???]";
        }
    }
}

class ModulationNilR extends Pictogram {

    public ModulationNilR(Board board) {
        super(board);
    }

    @Override
    public String toString() {
        return "[nil]";
    }
}

class ParseResult {
     final Pictogram pictogram;
     final int xOffset;

    public ParseResult(final Pictogram result, final int xOffset){
        this.pictogram = result;
        this.xOffset = xOffset;
    }
}

public class BoardDecipher {

    private final static Command[] commandsSortedBySize = Command.values().clone();

    private static boolean columnClear(Board board, int x) {
        for (int y = 0; y < board.height; y++) {
            if (board.getValue(x, y) == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean columnFilled(Board board, int x) {
        for (int y = 0; y < board.height; y++) {
            if (board.getValue(x, y) == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean rowClear(Board board, int y) {
        for (int x = 0; x < board.width; x++) {
            if (board.getValue(x, y) == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean rowFilled(Board board, int y) {
        for (int x = 0; x < board.width; x++) {
            if (board.getValue(x, y) == 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] getLeftTopMargins(final Board board, final int x, final int y) {
        // Find minimal non-clear X
        int minX = x;
        for (; minX < board.width; minX++) {
            if (!columnClear(board, minX)) {
                break;
            }
        }
        // Find minimal non-clear Y
        int minY = y;
        for (; minY < board.height; minY++) {
            if (!rowClear(board, minY)) {
                break;
            }
        }
        return new int[]{minX, minY};
    }

    public static int[] getRightBottomMargins(final Board board) {
        // Find max non-clear X
        int maxX = board.width - 1;
        for (; maxX >= 0; maxX--) {
            if (!columnClear(board, maxX)) {
                break;
            }
        }
        // Find max non-clear Y
        int maxY = board.height - 1;
        for (; maxY >= 0; maxY--) {
            if (!rowClear(board, maxY)) {
                break;
            }
        }
        return new int[]{maxX, maxY};
    }

    public static List<List<ParseResult>> decipher(final Board board) {
        Arrays.sort(commandsSortedBySize, (o1, o2) -> {
            // Compare by size
            return o2.getBoard().width * o2.getBoard().height - o1.getBoard().width * o1.getBoard().height;
        });

        final List<List<ParseResult>> result = new ArrayList<>();
        int sx = 0;
        int sy = 0;
        while (sy < board.height) {
            final int[] nextOffsetXY = getLeftTopMargins(board, sx, sy);
            int currentX = nextOffsetXY[0];
            int currentY = nextOffsetXY[1];
            if (currentY >= board.height) {
                break;
            }

            int rowHeight = 0;
            for (rowHeight = 0; rowHeight < board.height - currentY; rowHeight++) {
                if (rowClear(board, currentY + rowHeight)) {
                    break;
                }
            }

            final Board rowBoard = board.subBoard(currentX, currentY, board.width - currentX, rowHeight);
            result.add(parseRow(rowBoard));
            sy = currentY + rowHeight + 1;
        }
        return result;
    }

    private static List<ParseResult> parseRow(Board rowBoard) {
        int sx = 0;
        final List<ParseResult> result = new ArrayList<>();
        while (sx < rowBoard.width) {
            final int[] leftTopMargins = getLeftTopMargins(rowBoard, sx, 0);
            int currentX = leftTopMargins[0];
            if (currentX >= rowBoard.width) {
                break;
            }

            int pictogramWidth = 0;
            for (pictogramWidth = 0; pictogramWidth < rowBoard.width - currentX; pictogramWidth++) {
                if (columnClear(rowBoard, currentX + pictogramWidth)) {
                    break;
                }
            }
            final Board pictogram = rowBoard.subBoard(currentX, 0, pictogramWidth, rowBoard.height);
            result.add(new ParseResult(parsePictogram(trimMargins(pictogram)), currentX));

            sx = currentX + pictogramWidth + 1;
        }
        return result;
    }

    private static Board trimMargins(final Board board) {
        final int[] leftTopMargins = getLeftTopMargins(board, 0, 0);
        final int[] rightBottomMargins = getRightBottomMargins(board);
        final int left = leftTopMargins[0];
        final int top = leftTopMargins[1];
        final int width = rightBottomMargins[0] - left + 1;
        final int height = rightBottomMargins[1] - top + 1;
        if (left == 0 && top == 0 && width == board.width && height == board.height) {
            return board;
        }
        return board.subBoard(left, top, width, height);
    }

    // clear = 0 for ordinary numbers, 1 for variables
    private static BigInteger parseNumber(final Board pictogram, int clear) {
        // Number bit
        if (pictogram.getValue(0, 0) != clear) {
            return null;
        }
        final boolean positiveNumber = pictogram.width == pictogram.height;
        final boolean negativeNumber = pictogram.width == pictogram.height - 1;
        if (!positiveNumber && !negativeNumber) {
            return null;
        }
        for (int x = 1; x < pictogram.width; x++) {
            if (pictogram.getValue(x, 0) == clear) {
                return null;
            }
        }
        for (int y = 1; y < pictogram.height; y++) {
            if (pictogram.getValue(0, y) == clear) {
                return null;
            }
        }
        BigInteger number = BigInteger.ZERO;
        BigInteger power = BigInteger.ONE;
        final int size = Math.min(pictogram.width, pictogram.height);
        for (int y = 1; y < size; y++) {
            for (int x = 1; x < size; x++) {
                if (pictogram.getValue(x, y) != clear) {
                    number = number.add(power);
                }
                power = power.multiply(BigInteger.TWO);
            }
        }
        return positiveNumber ? number : number.negate();
    }

    private static Pictogram parseModulation(Board pictogram) {
        if (pictogram.width == 2 && pictogram.height == 1 &&
                (pictogram.getValue(0, 0) & pictogram.getValue(1, 0)) != 0) {
            return new ModulationNilR(pictogram);
        }
        if (pictogram.height == 2 && pictogram.width >= 3) {
            return new ModulationR(pictogram);
        }
        return null;
    }

    @NotNull
    private static Pictogram parsePictogram(Board pictogram) {
        if (pictogram.width == 1 && pictogram.height == 1) {
            return new DotR(pictogram);
        }
        final BigInteger variable = parseVariable(pictogram);
        if (variable != null) {
            return new VariableR(variable, pictogram);
        }
        final BigInteger number = parseNumber(pictogram, 0);
        if (number != null) {
            return new NumberR(number, pictogram);
        }
        // Parse commands
        for (final Command command : commandsSortedBySize) {
            if (pictogram.equals(command.getBoard())) {
                return new CommandR(command);
            }
        }
        final Pictogram picture = parsePicture(pictogram);
        if (picture != null) {
            return picture;
        }
        final Pictogram modulation = parseModulation(pictogram);
        if (modulation != null) {
            return modulation;
        }
        // Unknown object!
        return new Pictogram(pictogram);
    }

    private static Pictogram parsePicture(Board pictogram) {
        // Size check
        if (!(pictogram.width >= 10 && pictogram.height >= 10)) {
            return null;
        }
        // Corners check
        if (pictogram.getValue(0, 0) != 0 ||
                pictogram.getValue(pictogram.width - 1, 0) != 0 ||
                pictogram.getValue(pictogram.width - 1, pictogram.height - 1) != 0 ||
                pictogram.getValue(0, pictogram.height - 1) != 0) {
            return null;
        }
        // Check edges
        for (int x = 1; x < pictogram.width - 1; x++) {
            if (pictogram.getValue(x, 0) == 0 || pictogram.getValue(x, pictogram.height - 1) == 0) {
                return null;
            }
        }
        for (int y = 1; y < pictogram.height - 1; y++) {
            if (pictogram.getValue(0, y) == 0 || pictogram.getValue(pictogram.width - 1, y) == 0) {
                return null;
            }
        }
        return new PictureR(pictogram.subBoard(1, 1, pictogram.width - 2, pictogram.height - 2));
    }

    private static BigInteger parseVariable(Board pictogram) {
        // min size
        if (!(4 <= pictogram.width && 4 <= pictogram.height)) {
            return null;
        }
        if (!(columnFilled(pictogram, 0) && columnFilled(pictogram, pictogram.width - 1) &&
                rowFilled(pictogram, 0) && rowFilled(pictogram, pictogram.height - 1))) {
            return null;
        }
        // Check inversed number
        return parseNumber(pictogram.subBoard(1, 1, pictogram.width - 2, pictogram.height - 2), 1);
    }

    public static String dumpCommands(List<List<ParseResult>> decipher) {
        return decipher.stream().map(row -> {
                    final StringBuilder b = new StringBuilder();
                    for (final ParseResult parseResult: row) {
                        // Tabulate
                        b.append(" ".repeat(Math.max(b.length() == 0? 0 : 1, parseResult.xOffset / 2 - b.length())));
                        // Add pictogram
                        b.append(parseResult.pictogram.toString());
                    }
                    return b.toString();
                }
        ).collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) throws IOException {
        for (int i = 4; i <= 42; i++) {
            System.out.println("#" + i);
            final Board board = PngParser.loadPng("message" + i + ".png", 4, 4);
            dumpBoard(board);
        }
        // https://twitter.com/icfpcontest2020/status/1284438144784490502
        dumpBoard(PngParser.loadPng("twitter_more.png", 4, 4));
    }

    private static void dumpBoard(Board board) {
        System.out.println(board.width + "x" + board.height);
        System.out.println(board.toString());
        System.out.println("Board");
        System.out.println(dumpCommands(BoardDecipher.decipher(board)));
        System.out.println();
    }
}
