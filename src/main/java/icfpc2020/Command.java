package icfpc2020;

import icfpc2020.galaxy.ENumber;
import icfpc2020.galaxy.EvalResult;

import java.io.IOException;
import java.util.List;

public enum Command {
    // 1 Number
    // 2 Negative Number
    // 8 Variable
    Equality("=", 0, 4, 3),
    Successor("inc", 1, 5, 4) ,
    Predecessor("dec", 1, 6, 4),
    Sum("add", 2, 7, 4),
    Product("mul", 2, 9, 4),
    IntegerDivision("div", 2, 10, 4),
    EqualityBooleans("eq", 2, 11, 4),
    StrictLessThan("lt", 2, 12, 4),
    Modulate("mod", 1, 13, 4),
    Demodulate("dem", 1, 14, 4),
    Send("send", 1, 15, 3, 3, 4, 4),
    Negate("neg", 1, 16, 3),
    App("ap", 2, 17, 2),
    S("s", 3, 18, 3),
    C("c", 3, 19, 3),
    B("b", 3, 20, 3),
    TrueK("t", 0, 21, 3),
    False("f", 0, 22, 3),
    PowerOfTwo("pwr2", 1, 23, 7),
    I("i", 1, 24, 2),
    Cons("cons", 3, 25, 5),
    CarFirst("car", 1, 26, 5),
    CdrTail("cdr", 1, 27, 5),
    Nil("nil", 1, 28, 3),
    IsNil("isnil",1,  29, 3),
    ListLPar("(", 0, 30, 1, 1, 3, 5),
    ListComma(",", 0, 30, 6, 1, 2, 5),
    ListRPar(")", 0, 30, 10, 1, 3, 5),
    Vector("vec", 0, 31, 6),
    Draw("draw", 1, 32, 6),
    Checkerboard("checkerboard", 2, 33, 6),
    MultipleDraw("multipledraw", 1, 34, 7),
    // TODO or mod cons?
    ModulateList("mod", 1, 35, 4),
    Send0Deadline("deadline0", 0, 36, 6),
    Send0("send0", 1, 36, 5, 9, 4, 4),
    Is0("is0", 3, 37, 5),
    Interact("interact", 3, 38, 6),
    InteractionProtocol("interact", 3, 39, 6),
    StatelessDrawingProtocol("statelessdraw", 2, 40, 14, 1, 7, 7),
    StatefulDrawingProtocol("statefuldraw", 2, 41, 14, 1, 7, 7),
    Galaxy("galaxy", 0, 42, 13, 1, 7, 7);

    private String code;
    private final int args;
    private Board board;

    Command(String code, int args, int message, int size) {
        this(code, args, message, 1, 1, size, size);
    }

    Command(String code, int args, int message, int x, int y, int width, int height) {
        this.code = code;
        this.args = args;
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

    public int getNumberOfArgs() {
        return args;
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

    public EvalResult apply(List<EvalResult> collect) {
        throw new UnsupportedOperationException();
    }
}

