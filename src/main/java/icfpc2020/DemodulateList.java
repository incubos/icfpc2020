package icfpc2020;

import icfpc2020.list.MList;
import icfpc2020.operators.Demodulate;
import icfpc2020.operators.Modulate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static MList demMList(Message m) {
        String s = m.toString();
        assert s.startsWith("11") : "Not a list";
        s = s.substring(2);
        MList result = new MList(), current = result;
        boolean value = false;
        Map<MList, MList> current2prev = new HashMap<>();
        while (!s.isEmpty()) {
            if (s.startsWith("11") && !value) {
                MList n = new MList();
                current.getList().add(n);
                current2prev.put(n, current);
                current = n;
                s = s.substring(2);
            } else if (s.startsWith("11") && value) {
                // skip comma after value
                value = false;
                s = s.substring(2);
            } else if (s.startsWith("00") && !value) {
                // add nil
                current.getList().add(new MList());
                value = true;
                s = s.substring(2);
            } else if (s.startsWith("00") && value) {
                current = current2prev.remove(current);
                s = s.substring(2);
            } else {
                var bi = Demodulate.dem(new MessageImpl(s));
                current.getList().add(new MList(bi));
                var modulate = Modulate.mod(bi).toString();
                s = s.substring(modulate.length());
                value = true;
            }
        }
        return result;
    }
}
