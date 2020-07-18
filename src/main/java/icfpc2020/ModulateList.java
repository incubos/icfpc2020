package icfpc2020;

import icfpc2020.operators.Modulate;

import java.util.List;
import java.util.Map;

public class ModulateList {
    // ap is a part of valid construct here, it's just not reflected in modulated result.
    // e.g. ap ap cons 0 nil = 1101000, with first two `ap`s ignored in modulation.
    private static final Map<Command, String> modulateDict = Map.of(Command.App, "",
                                                                    Command.Cons, "11",
                                                                    Command.Nil, "00",
                                                                    Command.ListLPar, "11",
                                                                    Command.ListComma, "11",
                                                                    Command.ListRPar, "00");

    private ModulateList() {
    }

    public static Message mod(List<Pictogram> parseResults) {
        var sb = new StringBuilder();
        parseResults.forEach(o -> {
            if (o instanceof CommandR) {
                var command = ((CommandR) o).command;
                if (!modulateDict.containsKey(command)) {
                    throw new UnsupportedOperationException("can only modulate lists");
                }
                sb.append(modulateDict.get(command));
            } else if (o instanceof NumberR) {
                var number = ((NumberR) o).n;
                sb.append(new Modulate(number).mod());
            }
        });
        return new MessageImpl(sb.toString());
    }
}
