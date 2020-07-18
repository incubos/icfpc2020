package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class False1Value implements LazyValue {
    @NotNull
    private final LazyValue left;

    public False1Value(@NotNull final LazyValue left) {
        this.left = left;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return arg;
    }

    @Override
    public String toString() {
        return "(f " + left + ")";
    }
}
