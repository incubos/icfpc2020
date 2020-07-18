package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public final class NegateValue implements LazyValue {
    public static final LazyValue INSTANCE = new NegateValue();

    private NegateValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new ConstantValue(arg.asConst().negate());
    }

    @Override
    public String toString() {
        return "neg";
    }
}
