package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class B1Value implements LazyValue {
    @NotNull
    private final LazyValue left;
    @NotNull
    private final LazyValue middle;

    public B1Value(
            @NotNull final LazyValue left,
            @NotNull final LazyValue middle) {
        this.left = left;
        this.middle = middle;
    }

    @NotNull
    @Override
    //    ap ap ap b x0 x1 x2   =   ap x0 ap x1 x2
    public LazyValue apply(@NotNull final LazyValue right) {
        return left.apply(middle.apply(right));
    }

    @Override
    public String toString() {
        return "((b " + left + ") " + middle + ")";
    }
}
