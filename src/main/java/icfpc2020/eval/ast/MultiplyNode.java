package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.Multiply2Value;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class MultiplyNode implements ASTNode {
    static final ASTNode INSTANCE = new MultiplyNode();

    private MultiplyNode() {
    }

    @Override
    public String toString() {
        return "mul";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return Multiply2Value.INSTANCE;
    }
}