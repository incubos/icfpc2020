package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.DecrementValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class DecrementNode implements ASTNode {
    static final ASTNode INSTANCE = new DecrementNode();

    private DecrementNode() {
    }

    @Override
    public String toString() {
        return "dec";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return DecrementValue.INSTANCE;
    }
}