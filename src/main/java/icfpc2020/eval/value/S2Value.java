package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class S2Value implements LazyValue {
    @NotNull
    private final LazyValue left;

    public S2Value(@NotNull final LazyValue left) {
        this.left = left;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new S1Value(left, arg);
    }

    @Override
    public String toString() {
        return "(s " + left + ")";
    }
}
