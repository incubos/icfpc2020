package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public final class NilValue implements LazyValue {
    public static final LazyValue INSTANCE = new NilValue();

    private NilValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return True2Value.INSTANCE;
    }

    @Override
    public String toString() {
        return "nil";
    }
}
