package icfpc2020.eval.value;

import icfpc2020.Message;
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

    default Message asBinary() {
        throw new UnsupportedOperationException();
    }

    default LazyValue force() {
        LazyValue previous;
        LazyValue current = this;
        do {
            previous = current;
            current = current.eval();
        } while (previous != current);
        return current;
    }

    default BigInteger asConst() {
        return force().asConst();
    }
}
