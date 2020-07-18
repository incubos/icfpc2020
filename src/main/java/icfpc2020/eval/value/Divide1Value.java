package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public class Divide1Value implements LazyValue {
    @NotNull
    private final LazyValue left;

    public Divide1Value(@NotNull final LazyValue left) {
        this.left = left;
    }

    @NotNull
    @Override
    public LazyValue apply(final LazyValue arg) {
        final BigInteger l = left.eval().asConst();
        final BigInteger r = arg.eval().asConst();
        return new ConstantValue(l.divide(r));
    }

    @Override
    public String toString() {
        return "(add " + left + ")";
    }
}
