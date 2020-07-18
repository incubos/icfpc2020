package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class Multiply2Value implements LazyValue {
    public static final LazyValue INSTANCE = new Multiply2Value();

    private Multiply2Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new Multiply1Value(arg);
    }

    @Override
    public String toString() {
        return "mul";
    }
}
