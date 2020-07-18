package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public class If01Value implements LazyValue {
    @NotNull
    private final LazyValue condition;
    @NotNull
    private final LazyValue trueValue;

    public If01Value(
            @NotNull final LazyValue condition,
            @NotNull final LazyValue trueValue) {
        this.condition = condition;
        this.trueValue = trueValue;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue falseValue) {
        return condition.asConst().equals(BigInteger.ZERO) ? trueValue : falseValue;
    }

    @Override
    public String toString() {
        return "((if0 " + condition + ") " + trueValue + ")";
    }
}
