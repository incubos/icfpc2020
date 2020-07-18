package icfpc2020.galaxy;

import icfpc2020.Command;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class CommandEval {
    private static final Set<Command> BOOL_COMMANDS = Set.of(Command.TrueK, Command.False);

    public static void evalAndUpdateArg(
            ECmd cmd,
            List<EvalResult> args,
            Map<String, EvalResult> universe,
            int index
    ) {
        if (args.get(index) != null) {
            cmd.args.set(index, args.get(index).eval(universe));
        }
    }

    public static EvalResult curry(EvalResult func, EvalResult value) {
        if (func instanceof ECmd) {
            final ECmd cmd = (ECmd) func;
            // Check required number of arguments
            final int requiredArgs = cmd.command == Command.App ? 1 : cmd.command.getNumberOfArgs();
            for (int i = 0; i < requiredArgs; i++) {
                final EvalResult arg = cmd.args.get(i);
                if (value != null) {
                    final EvalResult curryArg = curry(arg, value);
                    if (curryArg != null) {
                        cmd.args.set(i, curryArg);
                        return func;
                    }
                }
            }
            for (int i = 0; i < requiredArgs; i++) {
                if (cmd.args.get(i) == null) {
                    cmd.args.set(i, value);
                    return func;
                }
            }
        }
        return null;
    }

    public static EvalResult processOperand(
            ECmd cmd,
            List<EvalResult> args,
            Map<String, EvalResult> universe,
            Function<BigInteger, BigInteger> operator
    ) {
        evalAndUpdateArg(cmd, args, universe, 0);
        if (args.get(0) instanceof ENumber) {
            return new ENumber(operator.apply(((ENumber) args.get(0)).number));
        }
        // No reduction today!
        return cmd;
    }

    public static EvalResult processAp(
            ECmd cmd,
            List<EvalResult> args,
            Map<String, EvalResult> universe
    ) {
        final EvalResult func = args.get(0).eval(universe);
        cmd.args.set(0, func);
        final EvalResult funcArg = args.size() == 2 ? args.get(1) : null;
        if (funcArg == null) {
            if (!(func instanceof ECmd)) {
                return func;
            }
            final Command command = ((ECmd) func).command;
            if (BOOL_COMMANDS.contains(command)) {
                return func;
            }
            if (command == Command.I) {
                return func;
            }
        }
        final EvalResult curry = CommandEval.curry(func, funcArg);
        if (curry != null) {
            return curry;
        }
        // No reduction today
        if (funcArg == null) {
            return cmd;
        }
        final EvalResult arg = funcArg.eval(universe);
        return (((ECmd) func).command.apply((ECmd) func, List.of(arg), universe));
    }

    public static EvalResult processOperands(
            ECmd cmd,
            List<EvalResult> args,
            Map<String, EvalResult> universe,
            Function2<BigInteger, BigInteger, BigInteger> operator
    ) {
        evalAndUpdateArg(cmd, args, universe, 0);
        evalAndUpdateArg(cmd, args, universe, 1);

        if (args.get(0) instanceof ENumber && args.get(1) instanceof ENumber) {
            return new ENumber(operator.invoke(((ENumber) args.get(0)).number, ((ENumber) args.get(1)).number));
        }
        // No reduction today!
        return cmd;
    }

    public static EvalResult eqBooleans(
            ECmd cmd,
            List<EvalResult> args,
            Map<String, EvalResult> universe
    ) {
        evalAndUpdateArg(cmd, args, universe, 0);
        evalAndUpdateArg(cmd, args, universe, 1);
        final EvalResult arg0 = args.get(0);
        final EvalResult arg1 = args.get(1);
        if (arg0 instanceof ECmd && arg1 instanceof ECmd) {
            if (BOOL_COMMANDS.contains(((ECmd) arg0).command) &&
                    BOOL_COMMANDS.contains(((ECmd) arg0).command)) {
                return ((ECmd)arg0).command == ((ECmd) arg1).command
                        ? createFunction(Command.TrueK)
                        : createFunction(Command.False);
            }
        }
        // No reduction today!
        return cmd;
    }

    @NotNull
    private static ECmd createFunction(Command boolCommand) {
        return new ECmd(boolCommand, new ArrayList<>());
    }

    public static EvalResult strictLessThan(
            ECmd cmd,
            List<EvalResult> args,
            Map<String, EvalResult> universe
    ) {
        evalAndUpdateArg(cmd, args, universe, 0);
        evalAndUpdateArg(cmd, args, universe, 1);

        if (args.get(0) instanceof ENumber && args.get(1) instanceof ENumber) {
            return ((ENumber) args.get(0)).number.compareTo(((ENumber) args.get(1)).number) < 0
                    ? createFunction(Command.TrueK)
                    : createFunction(Command.False);
        }
        // No reduction today!
        return cmd;
    }
}
