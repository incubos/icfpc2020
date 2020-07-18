package icfpc2020;

import icfpc2020.operators.Demodulate;
import icfpc2020.operators.Modulate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Stream;

public class DemodulateList {

    private enum State {
        EMPTY,
        OPEN,
        CLOSED,
        COMMA,
        NUMBER
    }

    public static List<Pictogram> dem(Message m) {
        String s = m.toString();
        List<Pictogram> result = new ArrayList<>();
        State state = State.EMPTY;
        while (!s.isEmpty()) {
            if (s.startsWith("11")) {
                switch (state) {
                    case EMPTY, COMMA, OPEN -> {
                        result.add(new CommandR(Command.ListLPar));
                        state = State.OPEN;
                    }
                    default -> {
                        result.add(new CommandR(Command.ListComma));
                        state = State.COMMA;
                    }
                }
                s = s.substring(2);
            } else if (s.startsWith("00")) {
                switch (state) {
                    case EMPTY, OPEN -> result.add(new CommandR(Command.Nil));
                    default -> result.add(new CommandR(Command.ListRPar));
                }
                state = State.CLOSED;
                s = s.substring(2);
            } else {
                if (state == State.CLOSED) {
                    result.add(new CommandR(Command.ListComma));
                }
                var bi = new Demodulate(s).dem();
                result.add(new NumberR(bi, Board.EMPTY));
                var modulateLength = new Modulate(bi).mod().length();
                s = s.substring(modulateLength);
                state = State.NUMBER;
            }
        }
        return result;
    }
}
