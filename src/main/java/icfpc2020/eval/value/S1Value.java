package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class S1Value implements LazyValue {
    @NotNull
    private final LazyValue left;
    @NotNull
    private final LazyValue middle;

    public S1Value(
            @NotNull final LazyValue left,
            @NotNull final LazyValue middle) {
        this.left = left;
        this.middle = middle;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue right) {
        return left.apply(right).apply(middle.apply(right));
    }

    @Override
    public String toString() {
        return "((s " + left + ") " + middle + ")";
    }
}
