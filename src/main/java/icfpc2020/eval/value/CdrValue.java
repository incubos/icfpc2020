package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class CdrValue implements LazyValue {
    public static final LazyValue INSTANCE = new CdrValue();

    private CdrValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull LazyValue arg) {
        return new ApplyValue(arg, False2Value.INSTANCE);
    }

    @Override
    public String toString() {
        return "cdr";
    }
}
