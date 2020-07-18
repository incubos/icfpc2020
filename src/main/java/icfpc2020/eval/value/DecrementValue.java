package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public class DecrementValue implements LazyValue {
    @NotNull
    private final LazyValue value;

    public DecrementValue(@NotNull final LazyValue value) {
        this.value = value;
    }

    @NotNull
    @Override
    public LazyValue apply(final LazyValue... args) {
        final LazyValue argument = value.apply();
        assert argument instanceof ConstantValue;
        return new ConstantValue(((ConstantValue) argument).value.subtract(BigInteger.ONE));
    }

    @Override
    public String toString() {
        return "dec " + value;
    }
}
