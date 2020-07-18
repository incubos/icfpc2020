package icfpc2020.galaxy;

import icfpc2020.Command;
import kotlin.jvm.functions.Function2;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CommandEval {

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
        if (!(func instanceof ECmd) && funcArg == null) {
            return func;
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

        if (args.get(0) instanceof EBoolean && args.get(1) instanceof EBoolean) {
            return new EBoolean(((EBoolean) args.get(0)).value == ((EBoolean) args.get(1)).value);
        }
        // No reduction today!
        return cmd;
    }

    public static EvalResult strictLessThan(
            ECmd cmd,
            List<EvalResult> args,
            Map<String, EvalResult> universe
    ) {
        evalAndUpdateArg(cmd, args, universe, 0);
        evalAndUpdateArg(cmd, args, universe, 1);

        if (args.get(0) instanceof ENumber && args.get(1) instanceof ENumber) {
            return new EBoolean(((ENumber) args.get(0)).number.compareTo(((ENumber) args.get(1)).number) < 0);
        }
        // No reduction today!
        return cmd;
    }
}
