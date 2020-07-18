package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public final class IValue implements LazyValue {
    public static final LazyValue INSTANCE = new IValue();

    private IValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull LazyValue arg) {
        return arg;
    }

    @Override
    public String toString() {
        return "i";
    }
}
