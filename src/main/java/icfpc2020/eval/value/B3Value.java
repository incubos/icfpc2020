package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class B3Value implements LazyValue {
    public static final LazyValue INSTANCE = new B3Value();

    private B3Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new B2Value(arg);
    }

    @Override
    public String toString() {
        return "b";
    }
}
