package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class If02Value implements LazyValue {
    @NotNull
    private final LazyValue condition;

    public If02Value(@NotNull final LazyValue condition) {
        this.condition = condition;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue trueValue) {
        return new If01Value(condition, trueValue);
    }

    @Override
    public String toString() {
        return "(if0 " + condition + ")";
    }
}
