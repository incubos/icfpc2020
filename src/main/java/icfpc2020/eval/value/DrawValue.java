package icfpc2020.eval.value;

import icfpc2020.Draw;
import icfpc2020.ImageRenderer;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.*;

public class DrawValue implements LazyValue {
    public static final LazyValue INSTANCE = new DrawValue();

    private DrawValue() {
    }

    public void applyInternal(@NotNull final LazyValue arg,
                              final List<Draw.Coord> acc,
                              final BigInteger[] arr) {
        if (arg instanceof ConstantValue) {
            BigInteger bi = arg.eval().asConst();
            if (arr[0] == null)
                arr[0] = bi;
            else {
                acc.add(Draw.Coord.of(arr[0], bi));
                arr[0] = null;
            }
        } else if (arg instanceof NilValue) {
            if (arr[0] != null)
                throw new IllegalArgumentException("draw can't operate non-pairs");
        } else if (arg instanceof ConsValue) {
            if (arr[0] != null)
                throw new IllegalArgumentException("draw can't operate non-pairs");
            ConsValue consValue = (ConsValue) arg;
            applyInternal(consValue.left.force(), acc, arr);
            applyInternal(consValue.right.force(), acc, arr);
        } else {
            throw new UnsupportedOperationException("draw only support list of vecs");
        }
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        var acc = new ArrayList<Draw.Coord>();
        applyInternal(arg.force(), acc, new BigInteger[1]);
        return new ImageValue(acc);
    }

    @Override
    public String toString() {
        return "draw";
    }
}
