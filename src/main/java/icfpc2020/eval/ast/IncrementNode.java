package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.IncrementValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class IncrementNode implements ASTNode {
    static final ASTNode INSTANCE = new IncrementNode();

    private IncrementNode() {
    }

    @Override
    public String toString() {
        return "inc";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return IncrementValue.INSTANCE;
    }
}