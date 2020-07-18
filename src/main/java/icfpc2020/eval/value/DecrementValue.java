package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public final class DecrementValue implements LazyValue {
    public static final LazyValue INSTANCE = new DecrementValue();

    private DecrementValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull LazyValue arg) {
        return new ConstantValue(arg.asConst().subtract(BigInteger.ONE));
    }

    @Override
    public String toString() {
        return "dec";
    }
}
