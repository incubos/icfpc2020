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

    @Override
    public LazyValue eval() {
        return this;
    }

    @NotNull
    @Override
    //    ap ap ap c x0 x1 x2   =   ap ap x0 x2 x1
    public LazyValue apply(@NotNull final LazyValue right) {
        return new ApplyValue(
                new ApplyValue(
                        left,
                        right),
                middle);
    }

    @Override
    public String toString() {
        return "((c " + left + ") " + middle + ")";
    }
}
