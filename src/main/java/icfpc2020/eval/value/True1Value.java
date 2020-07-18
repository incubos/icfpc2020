package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class True1Value implements LazyValue {
    @NotNull
    private final LazyValue left;

    public True1Value(@NotNull final LazyValue left) {
        this.left = left;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return left;
    }

    @Override
    public String toString() {
        return "(t " + left + ")";
    }
}
