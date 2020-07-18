package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class False2Value implements LazyValue {
    public static final LazyValue INSTANCE = new False2Value();

    private False2Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new False1Value(arg);
    }

    @Override
    public String toString() {
        return "f";
    }
}
