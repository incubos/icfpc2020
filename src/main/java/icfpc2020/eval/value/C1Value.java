package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class C1Value implements LazyValue {
    @NotNull
    private final LazyValue left;
    @NotNull
    private final LazyValue middle;

    public C1Value(
            @NotNull final LazyValue left,
            @NotNull final LazyValue middle) {
        this.left = left;
        this.middle = middle;
    }

    @NotNull
    @Override
    //    ap ap ap c x0 x1 x2   =   ap ap x0 x2 x1
    public LazyValue apply(@NotNull final LazyValue right) {
        return left.apply(right).apply(middle);
    }

    @Override
    public String toString() {
        return "((c " + left + ") " + middle + ")";
    }
}
