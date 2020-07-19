package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class ConsValue implements LazyValue {
    @NotNull
    public final LazyValue left;
    @NotNull
    public final LazyValue right;

    public ConsValue(@NotNull final LazyValue left, @NotNull final LazyValue right) {
        this.left = left;
        this.right = right;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new ApplyValue(new ApplyValue(arg, left), right);
    }

    @Override
    public String toString() {
        return "(cons " + left + ", " + right + ")";
    }
}
