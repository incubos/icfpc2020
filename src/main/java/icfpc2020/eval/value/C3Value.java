package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class C3Value implements LazyValue {
    public static final LazyValue INSTANCE = new C3Value();

    private C3Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new C2Value(arg);
    }

    @Override
    public String toString() {
        return "c";
    }
}
