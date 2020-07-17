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
                b.append(getValue(x, y) == 1? '*' : ' ');
            }
            b.append("\n");
        }
        return "Board w x h " + width  + " x "+ height + "\n" + b.toString();

    }
}


