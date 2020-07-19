package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class F381Value implements LazyValue {
    @NotNull
    private final LazyValue left;

    public F381Value(final LazyValue left) {
        this.left = left;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        // TODO!
        throw new UnsupportedOperationException();

    }

    @Override
    public String toString() {
        return "(f38 " + left + ")";
    }
}
