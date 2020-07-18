package icfpc2020.galaxy;

import icfpc2020.Command;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public interface EvalResult {
    default EvalResult eval(Map<String, EvalResult> universe) {
        throw new IllegalStateException("Not implemented");
    }
}

class EVariable  implements EvalResult {
    private final String name;

    EVariable(final String name) {
        this.name = name;
    }

    @Override
    public EvalResult eval(Map<String, EvalResult> universe) {
        if (universe.containsKey(name)) {
            return universe.get(name);
        }
        throw new IllegalStateException("Not expected");
    }

    @Override
    public String toString() {
        return name;
    }
}

class ENumber implements EvalResult {
    final BigInteger number;

    ENumber(BigInteger number) {
        this.number = number;
    }

    @Override
    public EvalResult eval(Map<String, EvalResult> universe) {
        return this;
    }

    @Override
    public String toString() {
       return number.toString();
    }
}

class ECmd implements EvalResult {
    private final Command command;
    private final List<EvalResult> args;

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

class EAssign implements EvalResult {
    private final String name;
    private final EvalResult right;

    EAssign(final String name, final EvalResult right) {
        this.name = name;
        this.right = right;
    }

    @Override
    public EvalResult eval(Map<String, EvalResult> universe) {
        universe.put(name, right.eval(universe));
        return null;
    }

    @Override
    public String toString() {
       return name + " = " + right.toString();
    }
}

class EGalaxyAssign extends EAssign {
    EGalaxyAssign(final EvalResult right) {
        super("galaxy", right);
    }
}