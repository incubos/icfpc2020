package icfpc2020.eval.ast;

import icfpc2020.eval.value.ConstantValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.function.Function;

/**
 * {@code const}.
 *
 * @author incubos
 */
final class ConstantNode implements ASTNode {
    // TODO: interning
    @NotNull
    private final LazyValue value;

    ConstantNode(@NotNull final BigInteger value) {
        this.value = new ConstantValue(value);
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
