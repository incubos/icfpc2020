package icfpc2020;

import icfpc2020.operators.Demodulate;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ParseResult {
    final Board board;

    public ParseResult(Board board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "?";
    }
}

class NumberR extends ParseResult {
    final int n;

    public NumberR(int n, Board board) {
        super(board);
        this.n = n;
    }

    @Override
    public String toString() {
        return String.valueOf(n);
    }
}

class VariableR extends ParseResult {
    final int n;

    public VariableR(int n, Board board) {
        super(board);
        this.n = n;
    }

    @Override
    public String toString() {
        return "x" + n;
    }
}

class CommandR extends ParseResult {
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

class DotR extends ParseResult {

    public DotR(Board board) {
        super(board);
    }

    @Override
    public String toString() {
        return ".";
    }
}

class PictureR extends ParseResult {

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

class ModulationR extends ParseResult {

    public ModulationR(Board board) {
        super(board);
    }

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder();
        for (int x = 0; x < board.width; x++) {
            b.append(board.getValue(x, 0));
        }
        return "[" + new Demodulate(b.toString()).dem() + "]";
    }
}

public class BoardDecipher {

    private final static Command[] commandsSortedBySize = Command.values().clone();

    private static boolean columnClear(Board board, int x){
        for (int y = 0; y < board.height; y++) {
            if (board.getValue(x, y) == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean columnFilled(Board board, int x){
        for (int y = 0; y < board.height; y++) {
            if (board.getValue(x, y) == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean rowClear(Board board, int y){
        for (int x = 0; x < board.width; x++) {
            if (board.getValue(x, y) == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean rowFilled(Board board, int y){
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
        return new int[] {minX, minY};
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
        return new int[] {maxX, maxY};
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
        final List<ParseResult> result = new ArrayList<ParseResult>();
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
            result.add(parsePictogram(trimMargins(pictogram)));

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
    private static Integer parseNumber(final Board pictogram, int clear) {
        // Number bit
        if (pictogram.getValue(0, 0) != clear) {
            return null;
        }
        final boolean positiveNumber = pictogram.width == pictogram.height;
        final boolean negativeNumber = pictogram.width == pictogram.height - 1;
        if (!positiveNumber && ! negativeNumber) {
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
        int number = 0;
        int power = 1;
        final int size = Math.min(pictogram.width, pictogram.height);
        for (int y = 1; y < size; y++) {
            for (int x = 1; x < size; x++) {
                if (pictogram.getValue(x, y) != clear) {
                    number += power;
                }
                power *= 2;
            }
        }
        return positiveNumber? number : -number;
    }

    @NotNull
    private static ParseResult parsePictogram(Board pictogram) {
        if (pictogram.width == 1 && pictogram.height == 1) {
            return new DotR(pictogram);
        }
        final Integer variable = parseVariable(pictogram);
        if (variable != null) {
            return new VariableR(variable, pictogram);
        }
        final Integer number = parseNumber(pictogram, 0);
        if (number != null) {
            return new NumberR(number, pictogram);
        }
        for (final Command command : commandsSortedBySize) {
            if (pictogram.contains(command.getBoard(), 0, 0)) {
                return new CommandR(command);
            }
        }
        final ParseResult picture = parsePicture(pictogram);
        if (picture != null) {
            return picture;
        }
        if (pictogram.height == 2 && pictogram.width >= 3) {
            return new ModulationR(pictogram);
        }
        // Unknown object!
        return new ParseResult(pictogram);
    }

    private static ParseResult parsePicture(Board pictogram) {
        // Size check
        if (!(pictogram.width >= 10 && pictogram.height >= 10)) {
            return null;
        }
        // Corners check
        if (pictogram.getValue(0, 0) != 0 ||
                pictogram.getValue(pictogram.width-1, 0) != 0 ||
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

    private static Integer parseVariable(Board pictogram) {
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
        return decipher.stream().map( row ->
                row.stream().map(ParseResult::toString).collect(Collectors.joining(" "))
        ).collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) throws IOException {
        for (int i = 4; i <= 42; i++) {
            final Board board = PngParser.loadPng("message" + i + ".png", 4, 4);
            final List<List<ParseResult>> decipher = BoardDecipher.decipher(board);
            System.out.println("#" + i);
            System.out.println(dumpCommands(decipher));
            System.out.println();
        }
    }
}
