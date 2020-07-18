package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public final class ConstantValue implements LazyValue {
    @NotNull
    final BigInteger value;

    public ConstantValue(@NotNull final BigInteger value) {
        this.value = value;
    }

    @NotNull
    @Override
    public LazyValue apply(final LazyValue... args) {
        assert args.length == 0;
        return this;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
