package icfpc2020.galaxy;

import icfpc2020.Command;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ECmd implements EvalResult {
     public final Command command;
     public final List<EvalResult> args;

    ECmd(final Command command, final List<EvalResult> args) {
        this.command = command;
        this.args = args;
    }

    @Override
    public EvalResult eval(Map<String, EvalResult> universe) {
        return command.apply(this, args, universe);
    }

    @Override
    public String toString() {
        return command.toString() + "(" +
                args.stream().map(a -> a != null ? a.toString() : "_").collect(Collectors.joining(", ")) +
                ")";
    }

    @Override
    public String pp() {
        if (args.size() == 0) {
            return command.toString();
        }
        return command.toString() + " " + args.stream()
                .map(a -> a != null ? a.pp() : null)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));

    }
}
