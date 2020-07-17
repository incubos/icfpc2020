package icfpc2020;

import java.io.IOException;

public enum Command {
    // 1 Number
    // 2 Negative Number
    // 8 Variable
    Equality("=", 4, 3),
    Successor("inc", 5, 4),
    Predecessor("dec", 6, 4),
    Sum("add", 7, 4),
    Product("mul", 9, 4),
    IntegerDivision("div", 10, 4),
    EqualityBooleans("eq", 11, 4),
    StrictLessThan("lt", 12, 4),
    Modulate("mod", 13, 4),
    Demodulate("dem", 14, 4),
    Send("send", 15, 3, 3, 4, 4),
    Negate("neg", 16, 3),
    App("app", 17, 2),
    S("s", 18, 3),
    C("c", 19, 3),
    B("b", 20, 3),
    TrueK("t", 21, 3),
    False("f", 22, 3),
    PowerOfTwo("pwr2", 23, 7),
    I("i", 24, 2),
    Cons("cons", 25, 5),
    CarFirst("car", 26, 5),
    CdrTail("cdr", 27, 5),
    Nil("nil", 28, 3),
    IsNil("isnil", 29, 3),
    ListLPar("(", 30, 1, 1, 3, 5),
    ListComma(",", 30, 6, 1, 2, 5),
    ListRPar(")", 30, 10, 1, 3, 5),
    Vector("vec", 31, 6),
    Draw("draw", 32, 6),
    Checkerboard("checkerboard", 33, 6),
    MultipleDraw("multipledraw", 34, 7),
    // TODO or mod cons?
    ModulateList("mod", 35, 4),
    Send0("send0", 36, 6),
    Is0("is0", 37, 5),
    Interact("interact", 38, 6),
    InteractionProtocol("interact", 39, 6),
    StatelessDrawingProtocol("statelessdraw", 40, 14, 1, 7, 7),
    StatefulDrawingProtocol("statefuldraw", 41, 14, 1, 7, 7),
    Galaxy("galaxy", 42, 14, 1, 7, 7);

    private String code;
    private Board board;

    Command(String code, int message, int size) {
        this(code, message, 1, 1, size, size);
    }

    Command(String code, int message, int x, int y, int width, int height) {
        this.code = code;
        try {
            this.board = PngParser.loadPng("message" + message + ".png", 4, 4).subBoard(x, y, width, height);
        } catch (IOException e) {
            System.err.println("Failed to load command " + code + "\n" + e.getStackTrace());
        }
    }


    @Override
    public String toString() {
        return code;
    }

    public Board getBoard() {
        return board;
    }


    public static void main(String[] args) {
        final Command[] commands = Command.values();
        System.out.println("================================");
        for (int i = 0; i < commands.length; i++) {
            System.out.println(commands[i].code);
            System.out.println(commands[i].getBoard().toString());
        }
    }
}

