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

    // Cache
    private LazyValue cachedEval = null;

    public ApplyValue(
            @NotNull final LazyValue function,
            @NotNull final LazyValue argument) {
        this.function = function;
        this.argument = argument;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return eval().apply(arg);
    }

    @Override
    public Message asBinary() {
        return force().asBinary();
    }

    @NotNull
    @Override
    public LazyValue eval() {
        if (cachedEval == null) {
            cachedEval = function.apply(argument);
        }
        return cachedEval;
    }

    @Override
    public String toString() {
        return "ap " + function + " " + argument;
    }
}
