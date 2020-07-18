package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class CarValue implements LazyValue {
    public static final LazyValue INSTANCE = new CarValue();

    private CarValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull LazyValue arg) {
        return new ApplyValue(arg, True2Value.INSTANCE);
    }

    @Override
    public String toString() {
        return "car";
    }
}
