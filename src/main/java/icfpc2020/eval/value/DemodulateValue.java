package icfpc2020.eval.value;

import icfpc2020.operators.Demodulate;
import icfpc2020.operators.Modulate;
import org.jetbrains.annotations.NotNull;

public class DemodulateValue implements LazyValue {
    public static final LazyValue INSTANCE = new DemodulateValue();

    private DemodulateValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new ConstantValue(Demodulate.dem(arg.asBinary()));
    }

    @Override
    public String toString() {
        return "inc";
    }
}
