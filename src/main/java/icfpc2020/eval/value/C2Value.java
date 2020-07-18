package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class C2Value implements LazyValue {
    @NotNull
    private final LazyValue left;

    public C2Value(@NotNull final LazyValue left) {
        this.left = left;
    }

    @NotNull
    @Override
    public LazyValue apply(final LazyValue arg) {
        return new C1Value(left, arg);
    }

    @Override
    public String toString() {
        return "(c " + left + ")";
    }
}
