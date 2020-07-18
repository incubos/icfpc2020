package icfpc2020;

import icfpc2020.operators.Demodulate;
import icfpc2020.operators.Modulate;

import java.util.ArrayList;
import java.util.List;

public class DemodulateList {

    public static List<Pictogram> dem(Message m) {
        String s = m.toString();
        List<Pictogram> result = new ArrayList<>();
        while (!s.isEmpty()) {
            if (s.startsWith("11")) {
                result.add(new CommandR(Command.App));
                result.add(new CommandR(Command.App));
                result.add(new CommandR(Command.Cons));
                s = s.substring(2);
            } else if (s.startsWith("00")) {
                result.add(new CommandR(Command.Nil));
                s = s.substring(2);
            } else {
                var bi = Demodulate.dem(new MessageImpl(s));
                result.add(new NumberR(bi, Board.EMPTY));
                var modulateLength = Modulate.mod(bi).toString().length();
                s = s.substring(modulateLength);
            }
        }
        return result;
    }
}
