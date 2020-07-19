package icfpc2020.eval.value;

import icfpc2020.Draw;
import icfpc2020.Message;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.List;

/**
 * @author incubos
 */
public interface LazyValue {
    @NotNull
    default LazyValue apply(@NotNull LazyValue arg) {
        throw new UnsupportedOperationException();
    }

    default LazyValue eval() {
        return this;
    }

    default Message asBinary() {
        throw new UnsupportedOperationException();
    }

    default List<Draw.Coord> asImage() {
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
        final LazyValue forced = force();
        if (forced instanceof ConstantValue) {
            return forced.asConst();
        } else {
            throw new IllegalStateException();
        }
    }
}
