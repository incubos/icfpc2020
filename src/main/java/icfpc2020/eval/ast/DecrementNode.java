package icfpc2020.eval.ast;

import icfpc2020.eval.value.DecrementValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * {@code dec}.
 *
 * @author incubos
 */
final class DecrementNode implements ASTNode {
    @Override
    public String toString() {
        return "dec";
    }

    @NotNull
    @Override
    public LazyValue eval() {
        return new DecrementValue();
    }
}