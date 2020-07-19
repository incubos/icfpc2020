package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class Cons1Value implements LazyValue {
    @NotNull
    private final LazyValue left;

    public Cons1Value(@NotNull final LazyValue left) {
        this.left = left;
    }

    @NotNull
    @Override
    public LazyValue apply(final @NotNull LazyValue arg) {
        return new ConsValue(left.force(), arg.force());
    }

    @Override
    public String toString() {
        return "(cons " + left + ")";
    }
}
