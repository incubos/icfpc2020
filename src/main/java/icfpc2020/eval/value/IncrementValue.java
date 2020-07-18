package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public final class IncrementValue implements LazyValue {
    public static final LazyValue INSTANCE = new IncrementValue();

    private IncrementValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new ConstantValue(arg.asConst().add(BigInteger.ONE));
    }

    @Override
    public String toString() {
        return "inc";
    }
}
