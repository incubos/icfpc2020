package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public interface LazyValue {
    @NotNull
    default LazyValue apply(@NotNull LazyValue arg) {
        throw new UnsupportedOperationException();
    }

    default LazyValue eval() {
        throw new UnsupportedOperationException();
    }

    default BigInteger asConst() {
        throw new UnsupportedOperationException();
    }
}