package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public final class ApplyValue implements LazyValue {
    @NotNull
    private final LazyValue function;
    @NotNull
    private final LazyValue argument;

    public ApplyValue(
            @NotNull final LazyValue function,
            @NotNull final LazyValue argument) {
        this.function = function;
        this.argument = argument;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return eval().apply(arg.eval());
    }

    @NotNull
    @Override
    public LazyValue eval() {
        return function.apply(argument.eval());
    }

    @Override
    public String toString() {
        return "ap " + function + " " + argument;
    }
}
