package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class B2Value implements LazyValue {
    @NotNull
    private final LazyValue left;

    public B2Value(@NotNull final LazyValue left) {
        this.left = left;
    }

    @NotNull
    @Override
    public LazyValue apply(final @NotNull LazyValue arg) {
        return new B1Value(left, arg);
    }

    @Override
    public String toString() {
        return "(b " + left + ")";
    }
}
