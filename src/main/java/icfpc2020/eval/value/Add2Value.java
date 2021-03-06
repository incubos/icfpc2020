package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class Add2Value implements LazyValue {
    public static final LazyValue INSTANCE = new Add2Value();

    private Add2Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new Add1Value(arg);
    }

    @Override
    public String toString() {
        return "add";
    }
}
