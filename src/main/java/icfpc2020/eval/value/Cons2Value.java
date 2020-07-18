package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class Cons2Value implements LazyValue {
    public static final LazyValue INSTANCE = new Cons2Value();

    private Cons2Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new Cons1Value(arg);
    }

    @Override
    public String toString() {
        return "cons";
    }
}
