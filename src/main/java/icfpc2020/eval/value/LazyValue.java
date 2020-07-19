package icfpc2020.eval.value;

import icfpc2020.Draw;
import icfpc2020.Message;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

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

    default List<Draw.Coord> asImage() {
        throw new UnsupportedOperationException();
    }

    default BigInteger asConst() {
        LazyValue previous;
        LazyValue current = this;
        do {
            previous = current;
            current = current.eval();
        } while (previous != current);
        return current.asConst();
    }
}
