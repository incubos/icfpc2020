package icfpc2020;

import icfpc2020.galaxy.ECmd;
import icfpc2020.galaxy.EvalResult;
import icfpc2020.galaxy.CommandEval;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static icfpc2020.galaxy.CommandEval.processAp;
import static icfpc2020.galaxy.CommandEval.processOperand;

public enum Command {
    Equality("=", 0, 4, 3),
    Successor("inc", 1, 5, 4) {
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            return CommandEval.processOperand(cmd, args, universe, (o) -> o.add(new BigInteger("1")));
        }
    },
    Predecessor("dec", 1, 6, 4) {
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            return CommandEval.processOperand(cmd, args, universe, (o) -> o.add(new BigInteger("-1")));
        }
    },
    Sum("add", 2, 7, 4) {
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            return CommandEval.processOperands(cmd, args, universe, BigInteger::add);
        }
    },
    Product("mul", 2, 9, 4) {
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            return CommandEval.processOperands(cmd, args, universe, BigInteger::multiply);
        }
    },
    IntegerDivision("div", 2, 10, 4) {
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            return CommandEval.processOperands(cmd, args, universe, BigInteger::divide);
        }
    },
    EqualityBooleans("eq", 2, 11, 4){
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            return CommandEval.eqBooleans(cmd, args, universe);

        }
    },
    StrictLessThan("lt", 2, 12, 4) {
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            return CommandEval.strictLessThan(cmd, args, universe);
        }
    },
    Modulate("mod", 1, 13, 4),
    Demodulate("dem", 1, 14, 4),
    Send("send", 1, 15, 3, 3, 4, 4),
    Negate("neg", 1, 16, 3),
    App("ap", 2, 17, 2) {
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            return processAp(cmd, args, universe);
        }
    },
    S("s", 3, 18, 3),
    C("c", 3, 19, 3),
    B("b", 3, 20, 3),
    TrueK("t", 0, 21, 3){
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            if (args.size() != 0) {
                return args.get(0);
            }
            // No reduction
            return cmd;
        }
    },
    False("f", 0, 22, 3){
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            if (args.size() > 1) {
                return args.get(1);
            }
            // No reduction
            return cmd;
        }
    },
    PowerOfTwo("pwr2", 1, 23, 7){
        @Override
        public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
            return processOperand(cmd, args, universe, (o) -> BigInteger.valueOf(1).shiftLeft(o.intValue()));
        }
    },
    I("i", 1, 24, 2),
    Cons("cons", 3, 25, 5),
    CarFirst("car", 1, 26, 5),
    CdrTail("cdr", 1, 27, 5),
    Nil("nil", 1, 28, 3),
    IsNil("isnil", 1, 29, 3),
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

    public EvalResult apply(ECmd cmd, List<EvalResult> args, Map<String, EvalResult> universe) {
        throw new UnsupportedOperationException("Command " + toString() + " is not supported yet!");
    }
}

