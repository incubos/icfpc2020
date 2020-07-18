package icfpc2020;

public class Board {
    public final int width;
    public final int height;
    private final Message innerMessage;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        innerMessage = new MessageImpl(width * height);
    }

    public int getValue(int x, int y) {
        assert 0 <= x && x < width: "Illegal x";
        assert 0 <= y && y < height: "Illegal y";
        return innerMessage.getValueAt(x * height + y);
    }

    public void setValue(int x, int y, int value) {
        assert 0 <= x && x < width: "Illegal x";
        assert 0 <= y && y < height: "Illegal y";
        assert value == 0 || value == 1: "Illegal value";
        innerMessage.setValue(x * height + y, value);
    }

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                b.append(getValue(x, y) == 1? '.' : ' ');
            }
            b.append("\n");
        }
        return "Board w x h " + width  + " x "+ height + "\n" + b.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Board)) {
            return false;
        }
        final Board other = (Board) obj;
        if (other.width != width || other.height != height) {
            return false;
        }
        for (int x = 0; x < other.width; x++) {
            for (int y = 0; y < other.height; y++) {
                if (getValue(x, y) != other.getValue(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Creates a subboard copy
     */
    public Board subBoard(int x, int y, int width, int height) {
        assert 0 <= x && x + width <= this.width: "Illegal x, width";
        assert 0 <= y && y + height <= this.height: "Illegal y, width";
        final Board result = new Board(width, height);
        for (int x1 = 0; x1 < width; x1++) {
            for (int y1 = 0; y1 < height; y1++) {
                result.setValue(x1, y1, getValue(x + x1, y + y1));
            }
        }
        return result;
    }
}


