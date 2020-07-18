package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public final class Pwr2Value implements LazyValue {
    public static final LazyValue INSTANCE = new Pwr2Value();

    private Pwr2Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new ConstantValue(BigInteger.TWO.pow(arg.asConst().intValue()));
    }

    @Override
    public String toString() {
        return "pwr2";
    }
}
