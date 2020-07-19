package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public final class IsnilValue implements LazyValue {
    public static final LazyValue INSTANCE = new IsnilValue();

    private IsnilValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        if (arg.eval() instanceof NilValue) {
            return True2Value.INSTANCE;
        } else {
            return False2Value.INSTANCE;
        }
    }

    @Override
    public String toString() {
        return "isnil";
    }
}
