package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class True2Value implements LazyValue {
    public static final LazyValue INSTANCE = new True2Value();

    private True2Value() {
    }

    @Override
    public LazyValue eval() {
        return this;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new True1Value(arg);
    }

    @Override
    public String toString() {
        return "t";
    }
}
