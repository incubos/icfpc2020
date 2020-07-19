package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class F382Value implements LazyValue {
    public static final LazyValue INSTANCE = new F382Value();

    private F382Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new F381Value(arg);
    }

    @Override
    public String toString() {
        return "f38";
    }
}
