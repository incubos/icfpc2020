package icfpc2020.eval.value;

import icfpc2020.Message;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public final class ApplyValue implements LazyValue {
    @NotNull
    public final LazyValue function;
    @NotNull
    public final LazyValue argument;

    public ApplyValue(
            @NotNull final LazyValue function,
            @NotNull final LazyValue argument) {
        this.function = function;
        this.argument = argument;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return ValueCache.eval(this).apply(arg);
    }

    @Override
    public Message asBinary() {
        return eval().asBinary();
    }

    @NotNull
    @Override
    public LazyValue eval() {
        return function.apply(argument);
    }

    @Override
    public String toString() {
        return "ap " + function + " " + argument;
    }
}
