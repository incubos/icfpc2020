package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class Divide2Value implements LazyValue {
    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new Divide1Value(arg);
    }

    @Override
    public String toString() {
        return "div";
    }
}
