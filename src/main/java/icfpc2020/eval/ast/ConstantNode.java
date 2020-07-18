package icfpc2020.eval.ast;

import icfpc2020.eval.value.ConstantValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * {@code ap}.
 *
 * @author incubos
 */
final class ConstantNode implements ASTNode {
    @NotNull
    private final BigInteger value;
    // TODO: interning
    @NotNull
    private final LazyValue lazyValue;

    ConstantNode(@NotNull final BigInteger value) {
        this.value = value;
        this.lazyValue = new ConstantValue(value);
    }

    @NotNull
    @Override
    public LazyValue eval(final LazyValue... args) {
        assert args.length == 0;
        return lazyValue;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
