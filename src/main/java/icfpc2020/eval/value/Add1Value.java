package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public class Add1Value implements LazyValue {
    @NotNull
    private final LazyValue left;

    public Add1Value(@NotNull final LazyValue left) {
        this.left = left;
    }

    @NotNull
    @Override
    public LazyValue apply(final LazyValue arg) {
        final BigInteger l = left.asConst();
        final BigInteger r = arg.asConst();
        return new ConstantValue(l.add(r));
    }

    @Override
    public String toString() {
        return "(add " + left + ")";
    }
}
