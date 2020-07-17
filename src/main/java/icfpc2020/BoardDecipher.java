package icfpc2020;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private static boolean rowClear(Board board, int y){
        for (int x = 0; x < board.width; x++) {
            if (board.getValue(x, y) == 1) {
                return false;
            }
        }
        return true;
    }

    public static int[] getNextOffsets(final Board board, final int x, final int y) {
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

    public static List<List<ParseResult>> decipher(final Board board) {
        Arrays.sort(commandsSortedBySize, (o1, o2) -> {
            // Compare by size
            return o2.getBoard().width * o2.getBoard().height - o1.getBoard().width * o1.getBoard().height;
        });

        final List<List<ParseResult>> result = new ArrayList<>();
        int sx = 0;
        int sy = 0;
        while (sy < board.height) {
            final int[] nextOffsetXY = getNextOffsets(board, sx, sy);
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
            final int[] nextOffsetXY = getNextOffsets(rowBoard, sx, 0);
            int currentX = nextOffsetXY[0];
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
            result.add(parsePictogram(pictogram));

            sx = currentX + pictogramWidth + 1;
        }
        return result;
    }

    @NotNull
    private static ParseResult parsePictogram(Board pictogram) {
        if (pictogram.width == 1 && pictogram.height == 1) {
            return new DotR(pictogram);
        }
        ParseResult parseResult = null;
        for (final Command command : commandsSortedBySize) {
            if (pictogram.contains(command.getBoard(), 0, 0)) {
                parseResult = new CommandR(command);
                break;
            }
        }
        if (parseResult == null) {
            // No commands found!
            // TODO: parse number or variable
            parseResult = new ParseResult(pictogram);
        }
        return parseResult;
    }
}
