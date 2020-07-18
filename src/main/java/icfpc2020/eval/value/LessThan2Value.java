package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class LessThan2Value implements LazyValue {
    public static final LazyValue INSTANCE = new LessThan2Value();

    private LessThan2Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new LessThan1Value(arg);
    }

    @Override
    public String toString() {
        return "lt";
    }
}
