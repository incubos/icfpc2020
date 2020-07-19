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
                              final Stack<BigInteger> stack) {
        final LazyValue input = arg.eval();
        if (input instanceof ConstantValue) {
            BigInteger bi = arg.eval().asConst();
            if (stack.isEmpty())
                stack.push(bi);
            else {
                acc.add(Draw.Coord.of(stack.pop(), bi));
            }
        } else if (input instanceof ApplyValue) {
            ApplyValue applyValue = (ApplyValue) input;
            applyInternal(applyValue.function, acc, stack);
            applyInternal(applyValue.argument, acc, stack);
        }
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        var acc = new ArrayList<Draw.Coord>();
        applyInternal(arg, acc, new Stack<>());
        return new ImageValue(acc);
    }

    @Override
    public String toString() {
        return "draw";
    }
}
