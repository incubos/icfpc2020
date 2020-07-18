package icfpc2020.galaxy;

import icfpc2020.Command;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class ECmd implements EvalResult {
     final Command command;
     final List<EvalResult> args;

    ECmd(final Command command, final List<EvalResult> args) {
        this.command = command;
        this.args = args;
    }

    @Override
    public EvalResult eval(Map<String, EvalResult> universe) {
        return command.apply(args.stream().map(a -> a.eval(universe)).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        if (args.size() == 0) {
            return command.toString();
        }
        return command.toString() + " " + args.stream()
                .map(a -> a != null ? a.toString() : null)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }
}
