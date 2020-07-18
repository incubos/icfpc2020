package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class Equality2Value implements LazyValue {
    public static final LazyValue INSTANCE = new Equality2Value();

    private Equality2Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new Equality1Value(arg);
    }

    @Override
    public String toString() {
        return "eq";
    }
}
