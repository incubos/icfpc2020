package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public class Equality1Value implements LazyValue {
    @NotNull
    private final LazyValue left;

    public Equality1Value(@NotNull final LazyValue left) {
        this.left = left;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        final BigInteger l = left.asConst();
        final BigInteger r = arg.asConst();
        if (l.equals(r)) {
            return True2Value.INSTANCE;
        } else {
            return False2Value.INSTANCE;
        }
    }

    @Override
    public String toString() {
        return "(eq " + left + ")";
    }
}
