package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public final class NegateValue implements LazyValue {
    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new ConstantValue(arg.eval().asConst().negate());
    }

    @Override
    public String toString() {
        return "neg";
    }
}
