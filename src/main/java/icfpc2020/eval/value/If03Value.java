package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class If03Value implements LazyValue {
    public static final LazyValue INSTANCE = new If03Value();

    private If03Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue condition) {
        return new If02Value(condition);
    }

    @Override
    public String toString() {
        return "if0";
    }
}
