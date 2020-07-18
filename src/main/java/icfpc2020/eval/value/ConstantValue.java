package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public final class ConstantValue implements LazyValue {
    // TODO: Intern
    @NotNull
    private final BigInteger value;

    public ConstantValue(@NotNull final BigInteger value) {
        this.value = value;
    }

    @Override
    public LazyValue eval() {
        return this;
    }

    @Override
    public BigInteger asConst() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
